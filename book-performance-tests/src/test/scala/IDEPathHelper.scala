import java.nio.file.Paths
import java.util.Objects.requireNonNull

object IDEPathHelper {

  private val projectRootDir = Paths.get(requireNonNull(getClass.getResource("gatling.conf"), "Couldn't locate gatling.conf").toURI).getParent.getParent.getParent
  //private val projectRootDir = Paths.get(getClass.getClassLoader.getResource("gatling.conf").toURI).getParent.getParent.getParent

  private val mavenTargetDirectory = projectRootDir.resolve("target")
  private val mavenSrcTestDirectory = projectRootDir.resolve("src").resolve("test")

  val mavenSourcesDirectory = mavenSrcTestDirectory.resolve("scala")
  val resourcesDirectory = mavenSrcTestDirectory.resolve("resources")
  val mavenBinariesDirectory = mavenTargetDirectory.resolve("test-classes")
  val resultsDirectory = mavenTargetDirectory.resolve("gatling")
  val recorderConfigFile = resourcesDirectory.resolve("recorder.conf")


}
