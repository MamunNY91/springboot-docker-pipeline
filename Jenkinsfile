
pipeline{

    agent any
   
    stages{

       stage("Execute Test"){
            steps{
              echo "Executing test job"
            }
          
        }
     
        stage("Build Jar"){
          when{
            expression {
              BRANCH_NAME=='master'
            }
          }
            steps{
              echo "builing app"
            }
          
        }
          stage("Deploy"){
            when{
              expression
              {
                 BRANCH_NAME=='master'
              }
            }
            steps{
              echo "Deploying app"
            }
          
        }

    }
    
}

