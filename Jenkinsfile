#!/usr/bin/env groovy
@Library('jenkins-shared-library')_
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
        stage("Build Jar"){
            steps{
               script{
                buildJar()
               }
            }
          
        }
          stage("Build Image"){
            steps{
               script{
                 buildImage()
               }
            }
          
        }

    }
    
}

