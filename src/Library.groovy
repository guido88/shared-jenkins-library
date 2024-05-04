class Library implements Serializable{
  def script

  Library(script){
    this.script = script
  }

  def buildJar(){
    script.echo "Building Jar...."
    script.sh 'mvn clean package'
  }

  def buildImage(){
     script.echo "Building Docker Image...."
     script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
       script.sh 'docker build -t guido88/privaterepo:jma-2.0 .'
       script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
     }
  }

  def deploy(){
    script.echo "Deploying App...."
    script.sh 'docker push guido88/privaterepo:jma-2.0'

  }
}