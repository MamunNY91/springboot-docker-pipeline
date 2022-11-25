def externalSC
pipeline{

    agent any
    tools{
        maven 'maven-3.8'
    }
    stages{
      stage("init"){
            steps{
               script{
                 externalSC = load "script.groovy"
               }
            }
      }
        stage("Increment version") {
            steps{
                script{
                    echo "increment app version"
                    sh 'mvn build-helper:parse-version versions:set \
                    -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion} \
                     .\\\${parsedVersion.nextIncrementalVersion} versions:commit'

                }
            }

        }
        stage("Build Jar"){
            steps{
               script{
                 echo "Building the app"
                 sh "mvn package"
               }
            }
          
        }
          stage("Build Image"){
            steps{
//               script{
//                 externalSC.buildAndPushImage()
//               }
                echo "builing image"
            }
          
        }

    }
    
}

