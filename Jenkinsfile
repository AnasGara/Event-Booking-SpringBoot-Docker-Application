pipeline {
  agent any
  tools {
    maven 'maven'
  }
  stages {
    stage ("Clean up"){
      steps {
        deleteDir()
      }
    }
    stage ("Clone repo"){
      steps {
        sh "git clone https://github.com/AnasGara/jenkinsHello"
      }
    }
    stage(" Generate backend image"){
      steps{
        dir("jenkinsHello"){
          sh "mvn clean install"
          sh "docker build -t JenkinsDockerDone"
        }
      }
    }
    stage ("Run docker compose")
    {
      steps{
        dir("jenkinsHello")
        {
          sh "docker compose up -d"
        }
      }
    }
  }
  
}
