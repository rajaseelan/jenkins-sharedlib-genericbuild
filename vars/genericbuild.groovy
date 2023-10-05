def call (Map config=[:]) {
    node {
        stage('SCM') {
            echo 'Gathering code from version control'
            git branch: '${branch}', url: 'https://github.com/rajaseelan/jenkins-go.git'
        }
        stage('Build') {
            try {
                echo "Building Go App"
                sh 'go version'
                sh 'go build -o main -a main.go'
                releasenotes(changes: "true");
            }catch(ex) {
                echo 'Something went wrong'
                echo ex.toString()
                currentBuild.result = 'FAILURE'
            }
            finally {
                // cleanup
                echo 'In finally block, executing cleanup...'
            }
        }

        // stage('Gonna Fail') {
        //     try {
        //         echo 'THis will fail'
        //         sh 'go not-a-command'
        //     } catch(ex) {
        //         echo 'Something Went wrong'
        //         echo ex.toString()
        //         //currentBuild.result = 'FAILURE'
        //     } finally {
        //         echo "In Finally, everything's gonna be ok"
        //     }
        // }

        stage('Test') {
            echo 'Testing .....'
        }

        stage('Deploy') {
            echo 'Deploying ...'
        }
    }
}