import Library

def call(String version){
    return new Library(this).deploy(version)
}