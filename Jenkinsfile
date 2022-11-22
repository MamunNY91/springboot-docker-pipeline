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
                 sh "mvn package"
               }
            }
          
        }
          stage("Build Image"){
            steps{
               script{
                 echo "Building the docker image"
                 withCredentials([usernamePassword(credentialsId: 'docker-hub-cred',passwordVariable:'PASS',usernameVariable:'USER')])
                 {
                    sh 'docker build -t samny91/springboot-docker-pipeline:1.0 .'
                    sh "echo $PASS | docker login -u $USER --password-stdin"
                    sh "docker push samny91/springboot-docker-pipeline:1.0"
                 }
               }
            }
          
        }

    }
    
}

