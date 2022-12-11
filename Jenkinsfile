
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
            steps{
                script{


                        //the following files must be available on ec2
                        sh "kubectl version"
                }
            }
        }
    }


    
}

