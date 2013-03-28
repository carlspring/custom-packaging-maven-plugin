package org.carlspring.maven.artifacthandling;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;

import java.io.File;
import java.io.IOException;

/**
 * @author mtodorov
 * @goal attach-artifact
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

    /**
     * @parameter property="artifactPath"
     */
    public String artifactPath;


    @Override
    public void execute()
            throws MojoExecutionException, MojoFailureException
    {
        File file;
        if (artifactPath != null)
        {
            try
            {
                file = new File(artifactPath).getCanonicalFile();
            }
            catch (IOException e)
            {
                throw new MojoExecutionException("Could not find artifact file!", e);
            }
        }
        else
        {
            file = new File(basedir + "/target/",
                            project.getArtifactId() + "-" +
                            project.getVersion() + "." +
                            project.getPackaging());
        }

        Artifact artifact = project.getArtifact();
        artifact.setFile(file);

        project.setArtifact(artifact);

        getLog().info("Attached " + artifact.toString() + ".");
    }

    public String getBasedir()
    {
        return basedir;
    }

    public void setBasedir(String basedir)
    {
        this.basedir = basedir;
    }

    public String getArtifactPath()
    {
        return artifactPath;
    }

    public void setArtifactPath(String artifactPath)
    {
        this.artifactPath = artifactPath;
    }

}
