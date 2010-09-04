package net.jarlehansen.proto2javame.domain.metadata;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public class InputMetaData {
    private final String destinationDirectory;
    private final String protoLocation;

    public InputMetaData(final String destinationDirectory, final String protoLocation) {
        this.destinationDirectory = destinationDirectory;
        this.protoLocation = protoLocation;
    }

    public String getDestinationDirectory() {
        return destinationDirectory;
    }

    public String getProtoLocation() {
        return protoLocation;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("InputMetaData");
        sb.append("{destinationDirectory='").append(destinationDirectory).append('\'');
        sb.append(", protoLocation='").append(protoLocation).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
