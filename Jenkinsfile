pipeline {
    agent any

    stages {
    stage('Unit tests') {
       steps {
       // Run the maven build
              //  if (isUnix()) {
                  //  sh "'${mvnHome}/bin/mvn' clean test -Dtest=TestRunner"
               // } else {
                       M2_HOME='C:/Users/ANTONIO/eclipse-workspace/maven/apache-maven-3.5.2'
  PATH=$M2_HOME/bin
bat 'mvn --version'
               // }
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
