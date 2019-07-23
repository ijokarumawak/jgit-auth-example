package com.rumawaks.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;

public class Push {

    private static final String usage = "Missing required arguments. Usage: Push gitProjectRootDir remoteToPush userName password";

    public static void main(String[] args) {

        if (args.length != 4) {
            System.out.println(usage);
            System.exit(1);
            return;
        }

        final File gitProjectRootDir = new File(args[0]);
        final String remoteToPush = args[1];
        final String userName = args[2];
        final String password = args[3];

        final Iterable<PushResult> results;
        try {
            results = getPushResult(gitProjectRootDir, remoteToPush, userName, password);
            results.forEach(result -> System.out.println(result.getMessages()));
        } catch (IOException|GitAPIException e) {
            e.printStackTrace();
        }
    }

    private static Iterable<PushResult> getPushResult(File gitProjectRootDir, String remoteToPush, String userName, String password) throws IOException, GitAPIException {
        final CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(userName, password);

        final FileRepositoryBuilder builder = new FileRepositoryBuilder()
            .readEnvironment()
            .setMustExist(true)
            .addCeilingDirectory(gitProjectRootDir)
            .findGitDir(gitProjectRootDir);

        final Repository gitRepo = builder.build();
        final PushCommand pushCommand = new Git(gitRepo).push().setRemote(remoteToPush);
        pushCommand.setCredentialsProvider(credentialsProvider);

        return pushCommand.call();
    }

}
