# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: commander.proto
# Protobuf Python Version: 5.26.1
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import symbol_database as _symbol_database
from google.protobuf.internal import builder as _builder
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\x0f\x63ommander.proto\"!\n\x0e\x43ommandRequest\x12\x0f\n\x07\x63ommand\x18\x01 \x01(\t\"!\n\x0f\x43ommandResponse\x12\x0e\n\x06result\x18\x01 \x01(\t\"5\n\x04\x42ook\x12\n\n\x02id\x18\x01 \x01(\x05\x12\r\n\x05title\x18\x02 \x01(\t\x12\x12\n\npage_count\x18\x03 \x01(\x05\x32\x42\n\tCommander\x12\x35\n\x0eprocessCommand\x12\x0f.CommandRequest\x1a\x10.CommandResponse\"\x00\x42\r\n\tgeneratedP\x01\x62\x06proto3')

_globals = globals()
_builder.BuildMessageAndEnumDescriptors(DESCRIPTOR, _globals)
_builder.BuildTopDescriptorsAndMessages(DESCRIPTOR, 'commander_pb2', _globals)
if not _descriptor._USE_C_DESCRIPTORS:
  _globals['DESCRIPTOR']._loaded_options = None
  _globals['DESCRIPTOR']._serialized_options = b'\n\tgeneratedP\001'
  _globals['_COMMANDREQUEST']._serialized_start=19
  _globals['_COMMANDREQUEST']._serialized_end=52
  _globals['_COMMANDRESPONSE']._serialized_start=54
  _globals['_COMMANDRESPONSE']._serialized_end=87
  _globals['_BOOK']._serialized_start=89
  _globals['_BOOK']._serialized_end=142
  _globals['_COMMANDER']._serialized_start=144
  _globals['_COMMANDER']._serialized_end=210
# @@protoc_insertion_point(module_scope)
