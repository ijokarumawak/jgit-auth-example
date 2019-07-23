# JGit User Authentication example

This is a sample application sending a Git push command using [JGit](https://www.eclipse.org/jgit/).

## How to build

Clone this repository, and execute following Maven command:

```
mvn install
```

## Usage

1. Clone another Git remote repository to push commits with this program.
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

If the push command failed, you'll see error logs like below:

```
Caused by: org.eclipse.jgit.errors.TransportException: https://github.com/ijokarumawak/nifi.git: not authorized
        at org.eclipse.jgit.transport.TransportHttp.connect(TransportHttp.java:570)
        at org.eclipse.jgit.transport.TransportHttp.openPush(TransportHttp.java:466)
        at org.eclipse.jgit.transport.PushProcess.execute(PushProcess.java:160)
        at org.eclipse.jgit.transport.Transport.push(Transport.java:1367)
        at org.eclipse.jgit.api.PushCommand.call(PushCommand.java:170)
        ... 8 more
```