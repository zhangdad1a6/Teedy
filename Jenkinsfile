pipeline {
    agent any
    stages {
        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh 'mvn test -Dmaven.test.failure.ignore=true'
                }
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }
        stage('PMD') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh 'mvn pmd:pmd'
                }
            }
        }
        stage('JaCoCo') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh 'mvn jacoco:report'
                }
            }
        }
        stage('Javadoc') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh 'mvn javadoc:javadoc'
                }
            }
        }
        stage('Site') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh 'mvn site'
                }
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
    }
    
    post {
        always {
            archiveArtifacts artifacts: 'docs-web/target/*.war', fingerprint: true, allowEmptyArchive: true
            archiveArtifacts artifacts: 'docs-web/target/site/**', fingerprint: true, allowEmptyArchive: true
            archiveArtifacts artifacts: 'docs-web/target/pmd.xml', fingerprint: true, allowEmptyArchive: true
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
        }
        success {
            echo '构建成功！'
        }
        failure {
            echo '构建失败！'
        }
    }
}