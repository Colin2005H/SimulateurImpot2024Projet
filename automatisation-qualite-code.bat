REM JAVA_HOME=chemin vers votre jdk

REM Attention au version de java dans le fichier pom.xml de maven version des sources version de la cible
REM Un jdk n peut compiler du code source en version n-1
REM <properties>
REM     <maven.compiler.source>17</maven.compiler.source>
REM     <maven.compiler.target>17</maven.compiler.target>
REM     <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
REM </properties>


SET MAVEN_HOME=D:\Program Files\apache-maven-3.9.9
SET PATH=%MAVEN_HOME%\bin;%PATH%
REM Pour inclure JAVA_HOME dans le PATH : SET PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%PATH%
REM Un remarque pour declencher un build
REM verify lance les tests unitaires et vérifie la seuil de couverture
mvn clean package verify site
