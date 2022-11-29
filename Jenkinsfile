
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
        /*
        stage("deploy app on EC2")
                {
                    steps{
                        script{
                            def dockerCMD= 'docker run -d -p 8080:8080 samny91/springboot-docker-pipeline:1.1.17-40'
                            sshagent(['ec2-server-key']){
                                sh "ssh -o StrictHostKeyChecking=no ec2-user@44.203.104.203 $dockerCMD"
                            }
                        }
                    }
               }

         */
       // deploy using docker compose
        //docker compose file must be available on EC2
        stage("deploy multiple conatiner using docker-compose"){
            steps{
                script{
                    //def dockerComposeCMD = "docker-compose -f docker-compose.yaml up --detach"
                    def shellCMD = "bash ./server-cmds.sh"
                    sshagent(['ec2-server-key']){
                        //the following files must be available on ec2
                        sh "scp server-cmds.sh ec2-user@44.203.104.203:/home/ec2-user"
                        sh "scp docker-compose.yaml ec2-user@44.203.104.203:/home/ec2-user "
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@44.203.104.203 $shellCMD"
                    }
                }
            }
        }
    }


    
}

