pipeline {
    agent any

    tools {
        maven 'Maven 3.8.6' // ou o nome configurado no Jenkins
        jdk 'Java 11'       // ou o JDK que você estiver usando
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/CARINE00/advantageTesteInmetrics.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Gerar Relatório Allure') {
            steps {
                sh 'mvn allure:report'
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }

        stage('Publicar Relatórios') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/**', fingerprint: true
        }
    }
}
