# priority  :debug<info<warn<error
#you cannot specify every priority with different file for log4j
log4j.rootLogger=debug,error,sql

#sql
#debug log
#log4j.logger.org.apache.ibatis=error
log4j.logger.org.jing.web.db.util.mapper=sql
log4j.logger.sql=sql
log4j.appender.sql=org.apache.log4j.DailyRollingFileAppender 
log4j.appender.sql.DatePattern='_'yyyy-MM-dd'.log'
log4j.appender.sql.File=./log/sql.log
log4j.appender.sql.Append=true
log4j.appender.sql.Threshold=DEBUG
log4j.appender.sql.layout=org.apache.log4j.PatternLayout 
log4j.appender.sql.layout.ConversionPattern=[%d{yyyy-MM-dd HH:mm:ss SSS}][%t][%c->>-%M][%p] - %m%n