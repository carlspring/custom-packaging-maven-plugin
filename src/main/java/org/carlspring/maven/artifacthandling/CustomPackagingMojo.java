package org.carlspring.maven.artifacthandling;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;

import java.io.File;

/**
 * @author mtodorov
 * @goal   attach-artifact
 */
public class CustomPackagingMojo
        extends AbstractMojo
{

    /**
     * @parameter property="project"
     * @required
     * @readonly
     */
    private MavenProject project;

    /**
     * Maven ProjectHelper.
     *
     * @component
     * @readonly
     */
    private MavenProjectHelper projectHelper;

    /**
     * @parameter property="basedir"
     */
    public String basedir;


    @Override
    public void execute()
            throws MojoExecutionException, MojoFailureException
    {
        File file = new File(basedir +"/target/",
                             project.getArtifactId() +"-" +
                             project.getVersion() + "." +
                             project.getPackaging());

        Artifact artifact = project.getArtifact();
        artifact.setFile(file);

        project.setArtifact(artifact);

        getLog().info("Attached " + artifact.toString() +".");
    }

}
