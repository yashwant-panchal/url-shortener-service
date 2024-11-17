/**
 * This Groovy file is used to generate JOOQ DAO, POJOs, and a fluent API for writing SQL queries using generated classes.
 *
 * Plugin documentation: https://github.com/etiennestuder/gradle-jooq-plugin
 */

apply plugin: 'nu.studer.jooq'

//Properties properties = new Properties()
//File propertiesFile = new File('/application.properties')
//propertiesFile.withInputStream {
//    properties.load(it)
//}

dependencies {
    jooqGenerator 'com.h2database:h2:2.2.220'
    implementation 'com.h2database:h2:2.2.220' // Add H2 database dependency
}


// Configure JOOQ
jooq {
    // Use jOOQ version defined in Spring Boot
    version = dependencyManagement.importedProperties['jooq.version']

    configurations {
        main {
            generationTool {
                jdbc {
                    driver = 'org.h2.Driver'
                    url = 'jdbc:h2:file:./src/main/resources/db/urlShortenerDB'
                    user = 'sa'
                    password = ''

                    println("driver = "+driver+", url = "+url+", user = "+user+", pass = "+password)
                }
                generator {
                    name = 'org.jooq.codegen.DefaultGenerator'
                    database {
                        name = 'org.jooq.meta.h2.H2Database'
                        inputSchema = 'urlShortener'  // change to desired DB schema
                        excludes = 'API_OUTPUT_.*|DATABASECHANGELOG.*'
                        forcedTypes {
                            forcedType {
                                name = 'BOOLEAN'
                                includeTypes = "(?i:TINYINT(UNSIGNED)?\\(1\\))"
                            }
                        }
                    }
                    generate {
                        relations = false
                        records = false
                        pojos = true
                        immutablePojos = false
                        daos = true
                        validationAnnotations = true
                        javaTimeTypes = true
                    }
                    target {
                        packageName = 'com.url.shortener.service.generated.jooq' // Adjust as per your package structure
                        directory = "${buildDir}/generated-sources/jooq" // Output directory
                    }
                    strategy {
                        name = 'org.jooq.codegen.DefaultGeneratorStrategy'
                    }
                }
            }
        }
    }
}
