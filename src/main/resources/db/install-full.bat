@echo off
REM Copyright (c) 2018-2019 by Pavlo Bakhmach.  All rights reserved
REM "Movie Land" full install from scratch scrit for PostgreSQL at Windows

SET server=localhost
SET /P server="Server [%server%]: "

SET port=5432
SET /P port="Port [%port%]: "

SET username=postgres
SET /P username="Username [%username%]: "

SET path_to_psql=C:\Program Files\PostgreSQL\10\bin
SET /P path_to_psql="Path to psql.exe [%path_to_psql%]: "


for /f "delims=" %%a in ('chcp ^|find /c "932"') do @ SET CLIENTENCODING_JP=%%a
if "%CLIENTENCODING_JP%"=="1" SET PGCLIENTENCODING=SJIS
if "%CLIENTENCODING_JP%"=="1" SET /P PGCLIENTENCODING="Client Encoding [%PGCLIENTENCODING%]: "

REM Run psql
"%path_to_psql%\psql.exe" -h %server% -U %username% -p %port%  -a -f create_movieland.sql

