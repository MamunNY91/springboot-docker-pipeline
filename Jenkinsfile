
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
        stage("deploy app on EC2")
                {
                    steps{
                        script{
                            def dockerCMD = 'docker run -d -p 8080:8080 samny91/springboot-docker-pipeline:1.1.17-40'
                            sshagent(['ec2-server-key']){
                                sh 'ssh -o StrictHostKeyChecking=no ec2-user@44.203.104.203 $dockerCMD'
                            }
                        }
                    }
                }

    }
    
}

