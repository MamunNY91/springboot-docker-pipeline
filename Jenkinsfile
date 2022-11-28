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
                    //def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = sh (script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true)
                    echo "version ===========  $version"
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
                echo "building image"
                /*
              script{
                externalSC.buildAndPushImage()
               }

                 */

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
        stage("Commit version change") {
            steps{
                echo "Commit sdadadad "
                script{
                    //create username and password credentail on jenkins for github, but instaed of using password use PAT
                    withCredentials([usernamePassword(credentialsId: 'github-with-pat',passwordVariable:'PASS',usernameVariable:'USER')])
                            {
                                //we need to configure git for ex- provide email and name so that people can know who committed. If you
                                // want to set up globally meaning for all project u can use --global flag otherwise remove it. this is done
                                //only once. you can set this by loggin into jenkins server as well
                                //sh 'git config --global user.email "mamun@example.com"'
                                //sh 'git config --global user.name "mamun"'
                                sh 'git status'
                                sh 'git branch'
                                sh 'git config --list'
                                //local git repo on jenkins_server does not know about remote repo so we need to mention
                                sh 'git remote set-url origin https://$USER:$PASS@github.com/MamunNY91/springboot-docker-pipeline.git'
                                //commit version change so that a new build can use this version and increment it
                                sh 'git add .'
                                sh 'git commit -m "ci: version bumped "'
                                /*
                                 when jenkins checkout repo in order to start the pipeline , it does not checkout branch instead it checksout last commit hash.
                                 here we are specifying commit changes to last commit hash of branch feature/stripe_integration
                                 HEAD points to last commit hash.
                                 */
                                sh 'git push origin HEAD:feature/stripe_integration '

                            }
                }
            }
        }

    }
    
}

