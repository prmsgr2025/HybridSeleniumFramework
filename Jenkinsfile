
pipeline {

    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    parameters {

        choice(
            name: 'EXECUTION_MODE',
            choices: ['local', 'remote'],
            description: 'Execution mode'
        )

        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox', 'edge'],
            description: 'Browser selection'
        )

        choice(
            name: 'ENV',
            choices: ['qa', 'staging'],
            description: 'Application environment'
        )

        string(
            name: 'GRID_URL',
            defaultValue: 'http://localhost:4444',
            description: 'Selenium Grid URL'
        )
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
                bat 'mvn clean compile -DskipTests'
            }
        }

        stage('Execute UI Tests') {
            steps {
                bat """
                    mvn test ^
                    -DexecutionMode=${params.EXECUTION_MODE} ^
                    -Dbrowser=${params.BROWSER} ^
                    -Denv=${params.ENV} ^
                    -DgridUrl=${params.GRID_URL}
                """
            }
        }
    }

    post {

        always {

            junit(
                testResults: '**/target/surefire-reports/TEST-*.xml',
                allowEmptyResults: true
            )

            archiveArtifacts(
                artifacts: 'test-output/**,target/surefire-reports/**',
                allowEmptyArchive: true
            )

            publishHTML(target: [
                reportDir: 'test-output',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Report',
                alwaysLinkToLastBuild: true,
                keepAll: true,
                allowMissing: true
            ])
        }
    }
}
