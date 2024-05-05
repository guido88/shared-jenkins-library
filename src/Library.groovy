class Library implements Serializable{
  def script

  Library(script){
    this.script = script
  }

  def buildJar(){
    script.echo "Building Jar...."
    script.sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
    script.sh 'mvn clean package'

  }

  def buildImage(String version){
     script.echo "Building Docker Image...."
     script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
       script.sh "docker build -t guido88/privaterepo:$version ."
       script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
     }
  }

  def deploy(String version){
    script.echo "Deploying App...."
    script.sh "docker push guido88/privaterepo:$version"

  }

  def commitVersion(){
    script.echo "committing version update...."
    script.withCredentials([script.usernamePassword(credentialsId: 'gitlogin', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
      script.sh 'git config --global user.email "jenkins@example.com"'
      script.sh 'git config --global user.name "jenkins"'

      script.sh "git remote set-url origin https://${script.USER}:${script.PASS}@github.com/guido88/java-maven-app.git"
      script.sh "git add ."
      script.sh 'git commit -m "ci: version bump"'
      script.sh 'git push origin HEAD:master'
    }
  }

}