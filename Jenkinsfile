pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
        jdk 'Corretto-17'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                sh 'mvn clean package'
            }
            post {
                success {
                    echo 'Build successful.'
                }
                failure {
                    echo 'Build failed.'
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                sh 'mvn test'
            }
            post {
                success {
                    echo 'Tests passed.'
                }
                failure {
                    echo 'Tests failed.'
                }
            }
        }
        stage('Docker Build and Push') {
            steps {
                script {
                    docker.build('gov-employees-credit:${BUILD_NUMBER}').push()
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                sh 'docker-compose up -d'
            }
        }
    }
}
