class Library implements Serializable{
  def script

  Library(script){
    this.script = script
  }

  def buildJar(){
    script.echo "building Jar...."
  }

  def buildImage(){
    script.echo "building Image...."
  }

  def deploy(){
    script.echo "Deploying app...."
  }
}