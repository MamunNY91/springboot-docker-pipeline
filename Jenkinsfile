
pipeline{

    agent any
    tools{
        maven 'maven-3.8'
    }
    stages{

        stage("Build Jar"){
            steps{
               script{
                 echo "Building the app"

               }
            }
          
        }
          stage("Build Image"){
            steps{
                echo "building image"

            }
          
        }
        stage("deploy on k8s"){
            environment{
                AWS_ACCESS_KEY_ID = credentials('jenkins_aws_access_key')
                AWS_SECRET_ACCESS_KEY = credentials('jenkins_aws_secret_key')
            }
            steps{

                script{
                    //the following files must be available on ec2
                        sh "kubectl create deployment nginx-deployment --image=nginx"
                }
            }
        }
    }


    
}

