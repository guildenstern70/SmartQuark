/*
 * The SmartQuark Project
 * Copyright (c) Alessio Saltarin, 2021-26
 * This software is licensed under MIT License
 * See LICENSE
 */

package net.littlelite.smartquark.service

import jakarta.enterprise.context.ApplicationScoped
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.sql.Connection
import java.sql.Statement
import javax.sql.DataSource

@ApplicationScoped
class DBInitializer(private val personService: PersonService,
    private val dataSource: DataSource)
{

    private val logger: Logger = LoggerFactory.getLogger(DBInitializer::class.java)

    fun populateDB()
    {
        if (this.personService.getPersonCount() == 0L)
        {
            logger.info("Populating DB")

            val sqlStream = this::class.java.classLoader.getResourceAsStream("import.sql")
            if (sqlStream != null) {
                logger.info("Found import.sql on classpath, executing script...")
                try {
                    sqlStream.use { stream ->
                        dataSource.connection.use { conn ->
                            executeSqlScript(conn, stream)
                        }
                    }
                    logger.info("import.sql executed successfully")
                    return
                } catch (ex: Exception) {
                    logger.error("Failed to execute import.sql, falling back to programmatic population", ex)
                }
            } else {
                logger.info("import.sql not found on classpath, using programmatic population")
            }

            logger.info("Done programmatic population.")
        }
    }

    private fun executeSqlScript(conn: Connection, sqlStream: java.io.InputStream)
    {
        val reader = BufferedReader(InputStreamReader(sqlStream))
        val sb = StringBuilder()
        reader.useLines { lines ->
            lines.forEach { line ->
                val trimmed = line.trim()
                // skip comments and empty lines
                if (trimmed.isEmpty() || trimmed.startsWith("--")) return@forEach
                sb.append(line).append('\n')
            }
        }

        val script = sb.toString()
        val statements = script.split(Regex(";\\s*\n"))
        conn.autoCommit = false
        conn.createStatement().use { stmt: Statement ->
            for (s in statements) {
                val sql = s.trim()
                if (sql.isEmpty()) continue
                stmt.addBatch(sql)
            }
            stmt.executeBatch()
        }
        conn.commit()
    }

}
