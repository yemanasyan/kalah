## Kalah game project
The assessment was for Kalah with 6 pits and in each one 6 stones,
but developed configurable Kalah with **n** pits and **m** stones.

## Run
Run controller module (com.backbase.kalah.KalahApplication class) 
or build controller module by `mvn clean package` command and 
then run generated *.jar file by `java -j controller-${version}.jar`
**Note** instead of ${version} should be written right version of the project.
E.g. 0.0.1-SNAPSHOT

No database installation is needed, as for this test project is chosen in memory H2 DB. 

## Usage
Aster running the project it can be used by any rest client or swagger.
You can open Swagger by this link: **http://localhost:8080/swagger-ui.html**