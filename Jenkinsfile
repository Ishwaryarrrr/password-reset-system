pipeline {
    agent any

    environment {
        // This links to the credentials you will set up in Jenkins
        DOCKER_CREDS = credentials('dockerhub-credentials')
        IMAGE_NAME = "Ishwarya21/password-reset"
    }

    tools {
        // Tells Jenkins to use the Maven version you installed on the server
        maven 'Maven3' 
        jdk 'JDK17'
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Pulls the latest code from your GitHub repository
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                // Runs your JUnit tests and builds the Java application
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                // Uses your Dockerfile to create the container image
                sh 'docker build -t $IMAGE_NAME:latest .'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                // Logs into Docker Hub and pushes the new image
                sh 'echo $DOCKER_CREDS_PSW | docker login -u $DOCKER_CREDS_USR --password-stdin'
                sh 'docker push $IMAGE_NAME:latest'
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline has finished executing.'
        }
        success {
            echo 'Deployment successful! Ready for Kubernetes.'
        }
        failure {
            echo 'Pipeline failed. Check the logs above.'
        }
    }
}