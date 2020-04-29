pipeline {
    agent any
     
    stages {
    stage('Unit tests') {
       steps {
        call mvn clean test
    }   
 }
    
	stage("build & SonarQube analysis"){
 	 agent any
         steps {
	 withSonarQubeEnv('My SonarQube Server') {
                bat 'C:/Users/ANTONIO/Documents/sonar-scanner-4.2.0.1873-windows/bin/sonar-scanner'
             }
          }
      }

  stage("Quality Gate") {
            steps {
              timeout(time: 1, unit: 'HOURS') {
                  error "Pipeline aborted due to quality gate failure: ${qg.status}"
                  waitForQualityGate abortPipeline: true
               
            }
         }
     }
  }
}
