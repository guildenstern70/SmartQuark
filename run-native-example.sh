#!/usr/bin/env bash
#
# The SmartQuark Project
# Copyright (c) Alessio Saltarin, 2021-22
# This software is licensed under MIT License
# See LICENSE
#

export DB_KIND=postgresql
export DB_URL=jdbc:postgresql://[your_url]
export DB_USERNAME=[your_user]
export DB_PASSWORD=[your_pwd]
./build/SmartQuark-0.4.1-runner

