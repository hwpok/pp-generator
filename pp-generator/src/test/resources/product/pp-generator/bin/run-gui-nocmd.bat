@ECHO OFF
CD ..
@ECHO current directory %cd%
FOR %%F IN (%cd%\libs\*.jar) DO CALL :addcp %%F
GOTO extlibe
:addcp
SET CLASSPATH=%CLASSPATH%;%1
GOTO :eof
:extlibe
SET CLASSPATH
START javaw -Dfile.encoding=utf-8 Main

