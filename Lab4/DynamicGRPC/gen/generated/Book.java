// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: commander.proto

// Protobuf Java Version: 4.26.1
package generated;

/**
 * Protobuf type {@code Book}
 */
public final class Book extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:Book)
    BookOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 26,
      /* patch= */ 1,
      /* suffix= */ "",
      Book.class.getName());
  }
  // Use Book.newBuilder() to construct.
  private Book(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private Book() {
    title_ = "";
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return generated.CommanderOuterClass.internal_static_Book_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return generated.CommanderOuterClass.internal_static_Book_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            generated.Book.class, generated.Book.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private int id_ = 0;
  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  @java.lang.Override
  public int getId() {
    return id_;
  }

  public static final int TITLE_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object title_ = "";
  /**
   * <code>string title = 2;</code>
   * @return The title.
   */
  @java.lang.Override
  public java.lang.String getTitle() {
    java.lang.Object ref = title_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      title_ = s;
      return s;
    }
  }
  /**
   * <code>string title = 2;</code>
   * @return The bytes for title.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getTitleBytes() {
    java.lang.Object ref = title_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      title_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PAGE_COUNT_FIELD_NUMBER = 3;
  private int pageCount_ = 0;
  /**
   * <code>int32 page_count = 3;</code>
   * @return The pageCount.
   */
  @java.lang.Override
  public int getPageCount() {
    return pageCount_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (id_ != 0) {
      output.writeInt32(1, id_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(title_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 2, title_);
    }
    if (pageCount_ != 0) {
      output.writeInt32(3, pageCount_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, id_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(title_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(2, title_);
    }
    if (pageCount_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, pageCount_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof generated.Book)) {
      return super.equals(obj);
    }
    generated.Book other = (generated.Book) obj;

    if (getId()
        != other.getId()) return false;
    if (!getTitle()
        .equals(other.getTitle())) return false;
    if (getPageCount()
        != other.getPageCount()) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId();
    hash = (37 * hash) + TITLE_FIELD_NUMBER;
    hash = (53 * hash) + getTitle().hashCode();
    hash = (37 * hash) + PAGE_COUNT_FIELD_NUMBER;
    hash = (53 * hash) + getPageCount();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static generated.Book parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.Book parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.Book parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.Book parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.Book parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.Book parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.Book parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static generated.Book parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static generated.Book parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static generated.Book parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static generated.Book parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static generated.Book parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(generated.Book prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code Book}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Book)
      generated.BookOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return generated.CommanderOuterClass.internal_static_Book_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return generated.CommanderOuterClass.internal_static_Book_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              generated.Book.class, generated.Book.Builder.class);
    }

    // Construct using generated.Book.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      id_ = 0;
      title_ = "";
      pageCount_ = 0;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return generated.CommanderOuterClass.internal_static_Book_descriptor;
    }

    @java.lang.Override
    public generated.Book getDefaultInstanceForType() {
      return generated.Book.getDefaultInstance();
    }

    @java.lang.Override
    public generated.Book build() {
      generated.Book result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public generated.Book buildPartial() {
      generated.Book result = new generated.Book(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(generated.Book result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.id_ = id_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.title_ = title_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.pageCount_ = pageCount_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof generated.Book) {
        return mergeFrom((generated.Book)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(generated.Book other) {
      if (other == generated.Book.getDefaultInstance()) return this;
      if (other.getId() != 0) {
        setId(other.getId());
      }
      if (!other.getTitle().isEmpty()) {
        title_ = other.title_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      if (other.getPageCount() != 0) {
        setPageCount(other.getPageCount());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              id_ = input.readInt32();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 18: {
              title_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 24: {
              pageCount_ = input.readInt32();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private int id_ ;
    /**
     * <code>int32 id = 1;</code>
     * @return The id.
     */
    @java.lang.Override
    public int getId() {
      return id_;
    }
    /**
     * <code>int32 id = 1;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(int value) {

      id_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>int32 id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      id_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object title_ = "";
    /**
     * <code>string title = 2;</code>
     * @return The title.
     */
    public java.lang.String getTitle() {
      java.lang.Object ref = title_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        title_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string title = 2;</code>
     * @return The bytes for title.
     */
    public com.google.protobuf.ByteString
        getTitleBytes() {
      java.lang.Object ref = title_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        title_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string title = 2;</code>
     * @param value The title to set.
     * @return This builder for chaining.
     */
    public Builder setTitle(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      title_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string title = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearTitle() {
      title_ = getDefaultInstance().getTitle();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string title = 2;</code>
     * @param value The bytes for title to set.
     * @return This builder for chaining.
     */
    public Builder setTitleBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      title_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    private int pageCount_ ;
    /**
     * <code>int32 page_count = 3;</code>
     * @return The pageCount.
     */
    @java.lang.Override
    public int getPageCount() {
      return pageCount_;
    }
    /**
     * <code>int32 page_count = 3;</code>
     * @param value The pageCount to set.
     * @return This builder for chaining.
     */
    public Builder setPageCount(int value) {

      pageCount_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>int32 page_count = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearPageCount() {
      bitField0_ = (bitField0_ & ~0x00000004);
      pageCount_ = 0;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:Book)
  }

  // @@protoc_insertion_point(class_scope:Book)
  private static final generated.Book DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new generated.Book();
  }

  public static generated.Book getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Book>
      PARSER = new com.google.protobuf.AbstractParser<Book>() {
    @java.lang.Override
    public Book parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<Book> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Book> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public generated.Book getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

