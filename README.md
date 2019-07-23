# JGit User Authentication example

This is a sample application sending a Git push command using [JGit](https://www.eclipse.org/jgit/).

## Usage

1. Clone a Git remote repository to push commits with this program.
NOTE, use https protocol to clone in order to use userName/password authentication.

2. Go to the cloned repository. Create a commit.

```
git commit --allow-empty -m "Test commit."
```

3. Push the commit with this sample program.

```
cd jgit-auth-example
# gitProjectRootDir is the repository used at step 1 and 2.
# remoteToPush is a remote name, 'origin' for example.
mvn exec:java -Dexec.mainClass="com.rumawaks.git.Push" -Dexec.args="gitProjectRootDir remoteToPush userName password"
```