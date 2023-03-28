## compile
`mvn clean install`

## Postup
1. Nastav svoj vlastný folder do **ROOT_PROJECT_DIR**
2. Vyber jednu z mapovacích stratégií:

   - projekt -> zoznam packages : `ProjectToPackages`
   - projekt -> zoznam package.class : `ProjectToClasses`
   - package.class -> zoznam imports : `ClassToImports`
   - package -> projekt : `PackageToProjects`
   - package.class-> projekt : `ClassToProjects`
   - projekt -> referenced projects : `ProjectToProjects`
