# Introduction #

The wiki's goal is to provide detailed description for all environments.

# Development Environment #

  * [JDK/JRE 6u38 (x64)](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
  * [Apache Maven 3.0.4](http://maven.apache.org/download.cgi)
  * [Apache Tomcat 7.0.34.0](http://tomcat.apache.org/download-70.cgi)
  * [Apache Axis2/Java 1.6.2](http://axis.apache.org/axis2/java/core/)
  * [Apache jUDDI v3.1.3](http://www.apache.org/dyn/closer.cgi/juddi/3_1_3/)
  * [MySQL Community Server 5.5.29](http://dev.mysql.com/downloads/mysql/)
  * [MySQL Connector/J 5.1.22](http://dev.mysql.com/downloads/connector/j/)
  * [MySQL Workbench 5.2.45 CE](http://dev.mysql.com/downloads/workbench/)
  * [SQuirrel SQL Client 3.4.0](http://squirrel-sql.sourceforge.net/)
  * [soapUI 4.5.1](http://www.soapui.org/)

## Eclipse ##
  * [Eclipse Juno (4.2) SR1](http://www.eclipse.org/downloads/)
  * Maven Integration for Eclipse _by Eclipse.org EPL, version: 1.2.0.20120903-1050_
  * Dali Java Persistence Tools - JPA Diagram Editor _by Eclipse.org EPL, version: 1.1.1.v201208222210_

# Production Environment #

# Project Directory Structure #

  * [{$DWSC\_HOME}/]
    * [branches/]
    * [tags/]
    * [wiki/]
    * [trunk/]
      * [conf/]
      * [db/]
      * [docs/]
      * [scripts/]
      * [samples](samples.md)

  * _$DWSC\_HOME - project home directory
  *_branches_*_tags_*_wiki_- project documentation
  *_trunk_- project development mainline
  *_conf_- configuration files (mvn, tomcat, mysql, etc.)
  *_db_- ddl/dml SQL scripts
  *_docs_- documentation to supplement wikis
  *_scripts_*_samples