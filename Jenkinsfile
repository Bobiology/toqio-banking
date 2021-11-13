pipeline {
    environment {
    registry = "bobiologist/toqio-banking"
    registryCredential = 'dockerhub'
    dockerImage = ''
    gitRegistry = "https://github.com/Bobiology/toqio-banking.git"
    }
    agent {
        label 'docker'
    }
    //agent any
    tools {
        maven 'M2_HOME'
        jdk 'JAVA_HOME'
     }
    stages {
        stage('Initialize'){
            steps{
                echo "PATH = ${M2_HOME}/bin:${PATH}"
                echo "M2_HOME = /opt/maven"
            }
        }
      /* stage('Cloning Git') {
        steps {
            sh 'git clone $gitRegistry' 
           }
       }*/
       stage('Build Artifact') {
         steps {
           sh 'mvn -B -DskipTests clean package'
            }
       }
        stage('Building our image') {
            steps {
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }
        stage('Deploy Image') {
          steps{
            script {
                withCredentials([string(credentialsId: 'docker', variable: 'docker')]) {
                    sh 'docker login -u bobiologist -p ${docker}'
                }
                dockerImage.push()
             }
           }
        }
      stage('Remove Unused docker image') {
          steps{
              sh "docker rmi $registry:$BUILD_NUMBER"
            }
         }
    }
}