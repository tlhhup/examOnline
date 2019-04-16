pipeline {
  agent any
  stages {
    stage('change dir') {
      steps {
        sh '''cd exam-eureka
pwd'''
      }
    }
    stage('build') {
      steps {
        sh 'mvn clean package'
      }
    }
  }
}