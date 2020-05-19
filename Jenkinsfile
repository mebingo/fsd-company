pipeline {
  agent none
  environment {
    DOCKERHUBNAME = "ssn717"
  }
  stages {
    stage('maven Build') {
      agent {
        docker {
          image 'maven:3-alpine'
          args '-v /root/.m2:/root/.m2'
        }
      }
      steps {
        sh 'mvn -B -DskipTests clean package'
        // sh 'mvn package -Dmaven.test.skip=true'
        // sh 'mvn clean package'
      }
    }

    stage('docker build & push & run') {
      agent any
      steps {
        script {
          def REMOVE_FLAG_C = sh(returnStdout: true, script: "docker container ls -q --filter name=.*FSD-Company.*") != ""
          echo "REMOVE_FLAG_C: ${REMOVE_FLAG_C}"
          if(REMOVE_FLAG_C){
            sh 'docker container rm -f $(docker container ls -q --filter name=.*FSD-Company.*)'
          }
          def REMOVE_FLAG = sh(returnStdout: true, script: "docker image ls -q *${DOCKERHUBNAME}/company*") != ""
          echo "REMOVE_FLAG: ${REMOVE_FLAG}"
          if(REMOVE_FLAG){
            sh 'docker image rm -f $(docker image ls -q *${DOCKERHUBNAME}/company*)'
          }
        }

        withCredentials([usernamePassword(credentialsId: 'ssn717ID', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
          // sh 'docker login -u $USERNAME -p $PASSWORD'
          sh 'docker image build -t ${DOCKERHUBNAME}/company .'
          // sh 'docker push ${DOCKERHUBNAME}/company'
          // sh 'docker run -d -p 8755:8755 --network fsd-net --name fsdcompany ${DOCKERHUBNAME}/company'
          sh 'docker run -d -p 8755:8755 --memory=600M --network fsd-net --name FSD-Company ${DOCKERHUBNAME}/company'
        }
      }
    }

    stage('clean workspace') {
      agent any
      steps {
        // clean workspace after job finished
        cleanWs()
      }
    }
  }
}