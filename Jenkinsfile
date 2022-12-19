
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
        stage("provision server"){
            //tf will provision EC2 instance so that we can deploy our app on it
            environment{
                AWS_ACCESS_KEY_ID = credentials("jenkins_aws_access_key")
                AWS_SECRET_ACCESS_KEY = credentials("jenkins_aws_secret_key")
                TF_VAR_env_prefix = "test"
            }
            steps{
                script{
                    //dir is mentioned here becuase terraform 
                    // command will be executed from terraform directory
                    dir("terraform"){
                        sh "terraform init"
                        sh "terraform apply --auto-approve"
                        EC2_PUBLIC_IP = sh (
                            script: "terraform output ec2_public_ip"
                            returnStdout: true
                            ).trim()
                    }
                }
            }
        }
        
        stage("deploy app on EC2")
                {
                    steps{
                        echo "Waiting for EC2 server to initialize"
                        script{
                            sleep(time: 300, unit: "SECONDS")
                            def dockerCMD= 'docker run -d -p 8080:8080 samny91/springboot-docker-pipeline:1.1.17-40'
                            sshagent(['ssh-key-ec2']){
                                sh "ssh -o StrictHostKeyChecking=no ec2-user@${EC2_PUBLIC_IP} $dockerCMD"
                            }
                        }
                    }
               }

         
         /* 
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
        */
    }


    
}

