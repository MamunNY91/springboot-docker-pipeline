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
                    -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }

        }
        stage("Build Jar"){
            steps{
               script{
                 echo "Building the app"
                 sh "mvn clean package"
               }
            }
          
        }
          stage("Build Image"){
            steps{
//               script{
//                 externalSC.buildAndPushImage()
//               }
                script{

                    withCredentials([usernamePassword(credentialsId: 'docker-hub-cred',passwordVariable:'PASS',usernameVariable:'USER')])
                            {
                                //make sure to use doubkle quotes if you use env var
                                sh "docker build -t samny91/springboot-docker-pipeline:$IMAGE_NAME ."
                                sh 'echo $PASS | docker login -u $USER --password-stdin'
                                sh "docker push samny91/springboot-docker-pipeline:$IMAGE_NAME"
                            }
                }
            }
          
        }

    }
    
}

