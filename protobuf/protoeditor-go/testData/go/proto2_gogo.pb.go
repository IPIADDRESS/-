// Code generated by protoc-gen-gogo. DO NOT EDIT.
// source: proto/proto2_gogo.proto

package proto2_go_proto

import (
	fmt "fmt"
	proto "github.com/gogo/protobuf/proto"
	math "math"
)

// Reference imports to suppress errors if they are not otherwise used.
var _ = proto.Marshal
var _ = fmt.Errorf
var _ = math.Inf

// This is a compile-time assertion to ensure that this generated file
// is compatible with the proto package it is being compiled against.
// A compilation error at this line likely means your copy of the
// proto package needs to be updated.
const _ = proto.GoGoProtoPackageIsVersion3 // please upgrade the proto package

type Shapes int32

const (
	Shapes_UNKNOWN  Shapes = 0
	Shapes_TRIANGLE Shapes = 3
	Shapes_SQUARE   Shapes = 4
	Shapes_CIRCLE   Shapes = 999
)

var Shapes_name = map[int32]string{
	0:   "UNKNOWN",
	3:   "TRIANGLE",
	4:   "SQUARE",
	999: "CIRCLE",
}

var Shapes_value = map[string]int32{
	"UNKNOWN":  0,
	"TRIANGLE": 3,
	"SQUARE":   4,
	"CIRCLE":   999,
}

func (x Shapes) Enum() *Shapes {
	p := new(Shapes)
	*p = x
	return p
}

func (x Shapes) String() string {
	return proto.EnumName(Shapes_name, int32(x))
}

func (x *Shapes) UnmarshalJSON(data []byte) error {
	value, err := proto.UnmarshalJSONEnum(Shapes_value, data, "Shapes")
	if err != nil {
		return err
	}
	*x = Shapes(value)
	return nil
}

func (Shapes) EnumDescriptor() ([]byte, []int) {
	return fileDescriptor_dca6f14d875f6b4e, []int{0}
}

type WeirdEnumName int32

const (
	WeirdEnumName_weirdValue_name WeirdEnumName = 0
)

var WeirdEnumName_name = map[int32]string{
	0: "weirdValue_name",
}

var WeirdEnumName_value = map[string]int32{
	"weirdValue_name": 0,
}

func (x WeirdEnumName) Enum() *WeirdEnumName {
	p := new(WeirdEnumName)
	*p = x
	return p
}

func (x WeirdEnumName) String() string {
	return proto.EnumName(WeirdEnumName_name, int32(x))
}

func (x *WeirdEnumName) UnmarshalJSON(data []byte) error {
	value, err := proto.UnmarshalJSONEnum(WeirdEnumName_value, data, "WeirdEnumName")
	if err != nil {
		return err
	}
	*x = WeirdEnumName(value)
	return nil
}

func (WeirdEnumName) EnumDescriptor() ([]byte, []int) {
	return fileDescriptor_dca6f14d875f6b4e, []int{1}
}

type M1_NestedEnum int32

const (
	M1_UNKNOWN M1_NestedEnum = 0
	M1_RED     M1_NestedEnum = 1
	M1_GREEN   M1_NestedEnum = 2
	M1_BLUE    M1_NestedEnum = 3
)

var M1_NestedEnum_name = map[int32]string{
	0: "UNKNOWN",
	1: "RED",
	2: "GREEN",
	3: "BLUE",
}

var M1_NestedEnum_value = map[string]int32{
	"UNKNOWN": 0,
	"RED":     1,
	"GREEN":   2,
	"BLUE":    3,
}

func (x M1_NestedEnum) Enum() *M1_NestedEnum {
	p := new(M1_NestedEnum)
	*p = x
	return p
}

func (x M1_NestedEnum) String() string {
	return proto.EnumName(M1_NestedEnum_name, int32(x))
}

func (x *M1_NestedEnum) UnmarshalJSON(data []byte) error {
	value, err := proto.UnmarshalJSONEnum(M1_NestedEnum_value, data, "M1_NestedEnum")
	if err != nil {
		return err
	}
	*x = M1_NestedEnum(value)
	return nil
}

func (M1_NestedEnum) EnumDescriptor() ([]byte, []int) {
	return fileDescriptor_dca6f14d875f6b4e, []int{0, 0}
}

type M1_NestedM1_NestedEnum int32

const (
	M1_NestedM1_UNKNOWN          M1_NestedM1_NestedEnum = 0
	M1_NestedM1_KNOWN_KNOWNS     M1_NestedM1_NestedEnum = 1
	M1_NestedM1_KNOWN_UNKNOWNS   M1_NestedM1_NestedEnum = 2
	M1_NestedM1_UNKNOWN_UNKNOWNS M1_NestedM1_NestedEnum = 3
)

var M1_NestedM1_NestedEnum_name = map[int32]string{
	0: "UNKNOWN",
	1: "KNOWN_KNOWNS",
	2: "KNOWN_UNKNOWNS",
	3: "UNKNOWN_UNKNOWNS",
}

var M1_NestedM1_NestedEnum_value = map[string]int32{
	"UNKNOWN":          0,
	"KNOWN_KNOWNS":     1,
	"KNOWN_UNKNOWNS":   2,
	"UNKNOWN_UNKNOWNS": 3,
}

func (x M1_NestedM1_NestedEnum) Enum() *M1_NestedM1_NestedEnum {
	p := new(M1_NestedM1_NestedEnum)
	*p = x
	return p
}

func (x M1_NestedM1_NestedEnum) String() string {
	return proto.EnumName(M1_NestedM1_NestedEnum_name, int32(x))
}

func (x *M1_NestedM1_NestedEnum) UnmarshalJSON(data []byte) error {
	value, err := proto.UnmarshalJSONEnum(M1_NestedM1_NestedEnum_value, data, "M1_NestedM1_NestedEnum")
	if err != nil {
		return err
	}
	*x = M1_NestedM1_NestedEnum(value)
	return nil
}

func (M1_NestedM1_NestedEnum) EnumDescriptor() ([]byte, []int) {
	return fileDescriptor_dca6f14d875f6b4e, []int{0, 0, 0}
}

type M1 struct {
	SinglePrimitive   *int32          `protobuf:"varint,1,opt,name=single_primitive,json=singlePrimitive" json:"single_primitive,omitempty"`
	RepeatedPrimitive []int32         `protobuf:"varint,2,rep,name=repeated_primitive,json=repeatedPrimitive" json:"repeated_primitive,omitempty"`
	SingleString      *string         `protobuf:"bytes,3,opt,name=single_string,json=singleString" json:"single_string,omitempty"`
	RepeatedString    []string        `protobuf:"bytes,4,rep,name=repeated_string,json=repeatedString" json:"repeated_string,omitempty"`
	SingleBytes       []byte          `protobuf:"bytes,5,opt,name=single_bytes,json=singleBytes" json:"single_bytes,omitempty"`
	RepeatedBytes     [][]byte        `protobuf:"bytes,6,rep,name=repeated_bytes,json=repeatedBytes" json:"repeated_bytes,omitempty"`
	SingleEnum        *Shapes         `protobuf:"varint,7,opt,name=single_enum,json=singleEnum,enum=com.proto.test.golang.proto2.Shapes" json:"single_enum,omitempty"`
	RepeatedEnum      []Shapes        `protobuf:"varint,8,rep,name=repeated_enum,json=repeatedEnum,enum=com.proto.test.golang.proto2.Shapes" json:"repeated_enum,omitempty"`
	SingleBool        *bool           `protobuf:"varint,9,opt,name=single_bool,json=singleBool" json:"single_bool,omitempty"`
	RepeatedBool      []bool          `protobuf:"varint,10,rep,name=repeated_bool,json=repeatedBool" json:"repeated_bool,omitempty"`
	SingleMessage     *M1_NestedM1    `protobuf:"bytes,11,opt,name=single_message,json=singleMessage" json:"single_message,omitempty"`
	RepeatedMessage   []*M1_NestedM1  `protobuf:"bytes,12,rep,name=repeated_message,json=repeatedMessage" json:"repeated_message,omitempty"`
	TestMap           map[int32]int32 `protobuf:"bytes,13,rep,name=test_map,json=testMap" json:"test_map,omitempty" protobuf_key:"varint,1,opt,name=key" protobuf_val:"varint,2,opt,name=value"`
	// Types that are valid to be assigned to TestOneof:
	//	*M1_IntChoice
	//	*M1_StringChoice
	TestOneof            isM1_TestOneof       `protobuf_oneof:"test_oneof"`
	SingleEnum2          *M1_NestedEnum       `protobuf:"varint,16,opt,name=single_enum2,json=singleEnum2,enum=com.proto.test.golang.proto2.M1_NestedEnum" json:"single_enum2,omitempty"`
	SingleStringPiece    *string              `protobuf:"bytes,17,opt,name=single_string_piece,json=singleStringPiece" json:"single_string_piece,omitempty"`
	RepeatedStringPiece  []string             `protobuf:"bytes,18,rep,name=repeated_string_piece,json=repeatedStringPiece" json:"repeated_string_piece,omitempty"`
	Singlegroupfield     *M1_SingleGroupField `protobuf:"group,99,opt,name=SingleGroupField,json=singlegroupfield" json:"singlegroupfield,omitempty"`
	XXX_NoUnkeyedLiteral struct{}             `json:"-"`
	XXX_unrecognized     []byte               `json:"-"`
	XXX_sizecache        int32                `json:"-"`
}

func (m *M1) Reset()         { *m = M1{} }
func (m *M1) String() string { return proto.CompactTextString(m) }
func (*M1) ProtoMessage()    {}
func (*M1) Descriptor() ([]byte, []int) {
	return fileDescriptor_dca6f14d875f6b4e, []int{0}
}
func (m *M1) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_M1.Unmarshal(m, b)
}
func (m *M1) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_M1.Marshal(b, m, deterministic)
}
func (m *M1) XXX_Merge(src proto.Message) {
	xxx_messageInfo_M1.Merge(m, src)
}
func (m *M1) XXX_Size() int {
	return xxx_messageInfo_M1.Size(m)
}
func (m *M1) XXX_DiscardUnknown() {
	xxx_messageInfo_M1.DiscardUnknown(m)
}

var xxx_messageInfo_M1 proto.InternalMessageInfo

type isM1_TestOneof interface {
	isM1_TestOneof()
}

type M1_IntChoice struct {
	IntChoice int32 `protobuf:"varint,14,opt,name=int_choice,json=intChoice,oneof" json:"int_choice,omitempty"`
}
type M1_StringChoice struct {
	StringChoice string `protobuf:"bytes,15,opt,name=string_choice,json=stringChoice,oneof" json:"string_choice,omitempty"`
}

func (*M1_IntChoice) isM1_TestOneof()    {}
func (*M1_StringChoice) isM1_TestOneof() {}

func (m *M1) GetTestOneof() isM1_TestOneof {
	if m != nil {
		return m.TestOneof
	}
	return nil
}

func (m *M1) GetSinglePrimitive() int32 {
	if m != nil && m.SinglePrimitive != nil {
		return *m.SinglePrimitive
	}
	return 0
}

func (m *M1) GetRepeatedPrimitive() []int32 {
	if m != nil {
		return m.RepeatedPrimitive
	}
	return nil
}

func (m *M1) GetSingleString() string {
	if m != nil && m.SingleString != nil {
		return *m.SingleString
	}
	return ""
}

func (m *M1) GetRepeatedString() []string {
	if m != nil {
		return m.RepeatedString
	}
	return nil
}

func (m *M1) GetSingleBytes() []byte {
	if m != nil {
		return m.SingleBytes
	}
	return nil
}

func (m *M1) GetRepeatedBytes() [][]byte {
	if m != nil {
		return m.RepeatedBytes
	}
	return nil
}

func (m *M1) GetSingleEnum() Shapes {
	if m != nil && m.SingleEnum != nil {
		return *m.SingleEnum
	}
	return Shapes_UNKNOWN
}

func (m *M1) GetRepeatedEnum() []Shapes {
	if m != nil {
		return m.RepeatedEnum
	}
	return nil
}

func (m *M1) GetSingleBool() bool {
	if m != nil && m.SingleBool != nil {
		return *m.SingleBool
	}
	return false
}

func (m *M1) GetRepeatedBool() []bool {
	if m != nil {
		return m.RepeatedBool
	}
	return nil
}

func (m *M1) GetSingleMessage() *M1_NestedM1 {
	if m != nil {
		return m.SingleMessage
	}
	return nil
}

func (m *M1) GetRepeatedMessage() []*M1_NestedM1 {
	if m != nil {
		return m.RepeatedMessage
	}
	return nil
}

func (m *M1) GetTestMap() map[int32]int32 {
	if m != nil {
		return m.TestMap
	}
	return nil
}

func (m *M1) GetIntChoice() int32 {
	if x, ok := m.GetTestOneof().(*M1_IntChoice); ok {
		return x.IntChoice
	}
	return 0
}

func (m *M1) GetStringChoice() string {
	if x, ok := m.GetTestOneof().(*M1_StringChoice); ok {
		return x.StringChoice
	}
	return ""
}

func (m *M1) GetSingleEnum2() M1_NestedEnum {
	if m != nil && m.SingleEnum2 != nil {
		return *m.SingleEnum2
	}
	return M1_UNKNOWN
}

func (m *M1) GetSingleStringPiece() string {
	if m != nil && m.SingleStringPiece != nil {
		return *m.SingleStringPiece
	}
	return ""
}

func (m *M1) GetRepeatedStringPiece() []string {
	if m != nil {
		return m.RepeatedStringPiece
	}
	return nil
}

func (m *M1) GetSinglegroupfield() *M1_SingleGroupField {
	if m != nil {
		return m.Singlegroupfield
	}
	return nil
}

// XXX_OneofWrappers is for the internal use of the proto package.
func (*M1) XXX_OneofWrappers() []interface{} {
	return []interface{}{
		(*M1_IntChoice)(nil),
		(*M1_StringChoice)(nil),
	}
}

type M1_NestedM1 struct {
	SinglePrimitive      *int32                  `protobuf:"varint,99,opt,name=single_primitive,json=singlePrimitive" json:"single_primitive,omitempty"`
	RepeatedPrimitive    []int32                 `protobuf:"varint,100,rep,name=repeated_primitive,json=repeatedPrimitive" json:"repeated_primitive,omitempty"`
	SingleEnum           *M1_NestedM1_NestedEnum `protobuf:"varint,101,opt,name=single_enum,json=singleEnum,enum=com.proto.test.golang.proto2.M1_NestedM1_NestedEnum" json:"single_enum,omitempty"`
	XXX_NoUnkeyedLiteral struct{}                `json:"-"`
	XXX_unrecognized     []byte                  `json:"-"`
	XXX_sizecache        int32                   `json:"-"`
}

func (m *M1_NestedM1) Reset()         { *m = M1_NestedM1{} }
func (m *M1_NestedM1) String() string { return proto.CompactTextString(m) }
func (*M1_NestedM1) ProtoMessage()    {}
func (*M1_NestedM1) Descriptor() ([]byte, []int) {
	return fileDescriptor_dca6f14d875f6b4e, []int{0, 0}
}
func (m *M1_NestedM1) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_M1_NestedM1.Unmarshal(m, b)
}
func (m *M1_NestedM1) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_M1_NestedM1.Marshal(b, m, deterministic)
}
func (m *M1_NestedM1) XXX_Merge(src proto.Message) {
	xxx_messageInfo_M1_NestedM1.Merge(m, src)
}
func (m *M1_NestedM1) XXX_Size() int {
	return xxx_messageInfo_M1_NestedM1.Size(m)
}
func (m *M1_NestedM1) XXX_DiscardUnknown() {
	xxx_messageInfo_M1_NestedM1.DiscardUnknown(m)
}

var xxx_messageInfo_M1_NestedM1 proto.InternalMessageInfo

func (m *M1_NestedM1) GetSinglePrimitive() int32 {
	if m != nil && m.SinglePrimitive != nil {
		return *m.SinglePrimitive
	}
	return 0
}

func (m *M1_NestedM1) GetRepeatedPrimitive() []int32 {
	if m != nil {
		return m.RepeatedPrimitive
	}
	return nil
}

func (m *M1_NestedM1) GetSingleEnum() M1_NestedM1_NestedEnum {
	if m != nil && m.SingleEnum != nil {
		return *m.SingleEnum
	}
	return M1_NestedM1_UNKNOWN
}

type M1_SingleGroupField struct {
	InGroup              *int32   `protobuf:"varint,100,opt,name=in_group,json=inGroup" json:"in_group,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *M1_SingleGroupField) Reset()         { *m = M1_SingleGroupField{} }
func (m *M1_SingleGroupField) String() string { return proto.CompactTextString(m) }
func (*M1_SingleGroupField) ProtoMessage()    {}
func (*M1_SingleGroupField) Descriptor() ([]byte, []int) {
	return fileDescriptor_dca6f14d875f6b4e, []int{0, 2}
}
func (m *M1_SingleGroupField) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_M1_SingleGroupField.Unmarshal(m, b)
}
func (m *M1_SingleGroupField) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_M1_SingleGroupField.Marshal(b, m, deterministic)
}
func (m *M1_SingleGroupField) XXX_Merge(src proto.Message) {
	xxx_messageInfo_M1_SingleGroupField.Merge(m, src)
}
func (m *M1_SingleGroupField) XXX_Size() int {
	return xxx_messageInfo_M1_SingleGroupField.Size(m)
}
func (m *M1_SingleGroupField) XXX_DiscardUnknown() {
	xxx_messageInfo_M1_SingleGroupField.DiscardUnknown(m)
}

var xxx_messageInfo_M1_SingleGroupField proto.InternalMessageInfo

func (m *M1_SingleGroupField) GetInGroup() int32 {
	if m != nil && m.InGroup != nil {
		return *m.InGroup
	}
	return 0
}

type WeirdMessageName struct {
	SinglePrimitive        *int32   `protobuf:"varint,1,opt,name=single_primitive,json=singlePrimitive" json:"single_primitive,omitempty"`
	RepeatedPrimitiveCount *int32   `protobuf:"varint,3,opt,name=Repeated_primitiveCount,json=RepeatedPrimitiveCount" json:"Repeated_primitiveCount,omitempty"`
	XXX_NoUnkeyedLiteral   struct{} `json:"-"`
	XXX_unrecognized       []byte   `json:"-"`
	XXX_sizecache          int32    `json:"-"`
}

func (m *WeirdMessageName) Reset()         { *m = WeirdMessageName{} }
func (m *WeirdMessageName) String() string { return proto.CompactTextString(m) }
func (*WeirdMessageName) ProtoMessage()    {}
func (*WeirdMessageName) Descriptor() ([]byte, []int) {
	return fileDescriptor_dca6f14d875f6b4e, []int{1}
}
func (m *WeirdMessageName) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_WeirdMessageName.Unmarshal(m, b)
}
func (m *WeirdMessageName) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_WeirdMessageName.Marshal(b, m, deterministic)
}
func (m *WeirdMessageName) XXX_Merge(src proto.Message) {
	xxx_messageInfo_WeirdMessageName.Merge(m, src)
}
func (m *WeirdMessageName) XXX_Size() int {
	return xxx_messageInfo_WeirdMessageName.Size(m)
}
func (m *WeirdMessageName) XXX_DiscardUnknown() {
	xxx_messageInfo_WeirdMessageName.DiscardUnknown(m)
}

var xxx_messageInfo_WeirdMessageName proto.InternalMessageInfo

func (m *WeirdMessageName) GetSinglePrimitive() int32 {
	if m != nil && m.SinglePrimitive != nil {
		return *m.SinglePrimitive
	}
	return 0
}

func (m *WeirdMessageName) GetRepeatedPrimitiveCount() int32 {
	if m != nil && m.RepeatedPrimitiveCount != nil {
		return *m.RepeatedPrimitiveCount
	}
	return 0
}

func init() {
	proto.RegisterEnum("com.proto.test.golang.proto2.Shapes", Shapes_name, Shapes_value)
	proto.RegisterEnum("com.proto.test.golang.proto2.WeirdEnumName", WeirdEnumName_name, WeirdEnumName_value)
	proto.RegisterEnum("com.proto.test.golang.proto2.M1_NestedEnum", M1_NestedEnum_name, M1_NestedEnum_value)
	proto.RegisterEnum("com.proto.test.golang.proto2.M1_NestedM1_NestedEnum", M1_NestedM1_NestedEnum_name, M1_NestedM1_NestedEnum_value)
	proto.RegisterType((*M1)(nil), "com.proto.test.golang.proto2.M1")
	proto.RegisterMapType((map[int32]int32)(nil), "com.proto.test.golang.proto2.M1.TestMapEntry")
	proto.RegisterType((*M1_NestedM1)(nil), "com.proto.test.golang.proto2.M1.NestedM1")
	proto.RegisterType((*M1_SingleGroupField)(nil), "com.proto.test.golang.proto2.M1.SingleGroupField")
	proto.RegisterType((*WeirdMessageName)(nil), "com.proto.test.golang.proto2.weirdMessage_name")
}

func init() {
	proto.RegisterFile("golang/src/itest/resources/proto2_gogo.proto", fileDescriptor_dca6f14d875f6b4e)
}

var fileDescriptor_dca6f14d875f6b4e = []byte{
	// 797 bytes of a gzipped FileDescriptorProto
	0x1f, 0x8b, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0xff, 0x94, 0x55, 0x7f, 0x6f, 0xdb, 0x44,
	0x18, 0xae, 0xed, 0x26, 0x71, 0xde, 0x38, 0xc9, 0xe5, 0x3a, 0xc0, 0x54, 0x48, 0x33, 0x81, 0x0a,
	0x6f, 0xd0, 0x44, 0xb1, 0x10, 0x43, 0x13, 0xff, 0x2c, 0xc1, 0xb4, 0x15, 0x4d, 0x28, 0x97, 0x06,
	0x24, 0x24, 0x64, 0x79, 0xce, 0xcd, 0xb3, 0x48, 0x7c, 0x96, 0xed, 0x6c, 0xea, 0xd7, 0xe0, 0x4b,
	0x22, 0xf1, 0x29, 0xd0, 0xdd, 0xd9, 0x4e, 0xd2, 0xb1, 0xb5, 0xfd, 0x27, 0x3a, 0x3f, 0xf7, 0x3c,
	0xcf, 0xbd, 0x77, 0xef, 0x8f, 0xc0, 0x37, 0x21, 0x5b, 0xf9, 0x71, 0x38, 0xcc, 0xd2, 0x60, 0x18,
	0xe5, 0x34, 0xcb, 0x87, 0x29, 0xcd, 0xd8, 0x26, 0x0d, 0x68, 0x36, 0x4c, 0x52, 0x96, 0x33, 0xc7,
	0x0b, 0x59, 0xc8, 0x06, 0x62, 0x8d, 0x3f, 0x0b, 0xd8, 0x5a, 0x2e, 0x07, 0x9c, 0x3b, 0x90, 0x62,
	0x89, 0x38, 0xfd, 0x7f, 0x5b, 0xa0, 0x4e, 0x47, 0xf8, 0x09, 0xa0, 0x2c, 0x8a, 0xc3, 0x15, 0xf5,
	0x92, 0x34, 0x5a, 0x47, 0x79, 0xf4, 0x86, 0x9a, 0x8a, 0xa5, 0xd8, 0x35, 0xd2, 0x95, 0xf8, 0x55,
	0x09, 0xe3, 0x53, 0xc0, 0x29, 0x4d, 0xa8, 0x9f, 0xd3, 0xe5, 0x0e, 0x59, 0xb5, 0x34, 0xbb, 0x46,
	0x7a, 0xe5, 0xce, 0x96, 0xfe, 0x05, 0xb4, 0x0b, 0xe7, 0x2c, 0x4f, 0xa3, 0x38, 0x34, 0x35, 0x4b,
	0xb1, 0x9b, 0xc4, 0x90, 0xe0, 0x5c, 0x60, 0xf8, 0x2b, 0xe8, 0x56, 0x9e, 0x05, 0xed, 0xd0, 0xd2,
	0xec, 0x26, 0xe9, 0x94, 0x70, 0x41, 0xfc, 0x1c, 0x0a, 0xa1, 0xf7, 0xf2, 0x26, 0xa7, 0x99, 0x59,
	0xb3, 0x14, 0xdb, 0x20, 0x2d, 0x89, 0x8d, 0x39, 0x84, 0x4f, 0xa0, 0x12, 0x15, 0xa4, 0xba, 0xa5,
	0xd9, 0x06, 0x69, 0x97, 0xa8, 0xa4, 0xb9, 0x50, 0xa8, 0x3c, 0x1a, 0x6f, 0xd6, 0x66, 0xc3, 0x52,
	0xec, 0x8e, 0xf3, 0xe5, 0xe0, 0x43, 0x8f, 0x35, 0x98, 0xbf, 0xf6, 0x13, 0x9a, 0x11, 0x90, 0x42,
	0x37, 0xde, 0xac, 0xf1, 0x05, 0x54, 0xbe, 0xd2, 0x48, 0xb7, 0xb4, 0x7b, 0x1b, 0x19, 0xa5, 0x54,
	0x58, 0x3d, 0xae, 0x22, 0x7a, 0xc9, 0xd8, 0xca, 0x6c, 0x5a, 0x8a, 0xad, 0x97, 0x67, 0x8d, 0x19,
	0x5b, 0xf1, 0xa7, 0xdc, 0xde, 0x8c, 0x53, 0xc0, 0xd2, 0x6c, 0x7d, 0xeb, 0x22, 0x48, 0x57, 0xd0,
	0x29, 0x5c, 0xd6, 0x34, 0xcb, 0xfc, 0x90, 0x9a, 0x2d, 0x4b, 0xb1, 0x5b, 0xce, 0x93, 0x0f, 0x47,
	0x34, 0x1d, 0x0d, 0x66, 0x34, 0xcb, 0xe9, 0x72, 0x3a, 0x22, 0x45, 0xc2, 0xa6, 0x52, 0x8f, 0xaf,
	0x01, 0x55, 0xc7, 0x96, 0x9e, 0x86, 0xa5, 0x3d, 0xcc, 0xb3, 0xca, 0x6f, 0xe9, 0x7a, 0x0e, 0x3a,
	0x97, 0x78, 0x6b, 0x3f, 0x31, 0xdb, 0xc2, 0xed, 0xf4, 0x4e, 0xb7, 0x6b, 0x9a, 0xe5, 0x53, 0x3f,
	0x71, 0xe3, 0x3c, 0xbd, 0x21, 0x8d, 0x5c, 0x7e, 0xe1, 0xc7, 0x00, 0x51, 0x9c, 0x7b, 0xc1, 0x6b,
	0x16, 0x05, 0xd4, 0xec, 0xf0, 0xaa, 0x3d, 0x3f, 0x20, 0xcd, 0x28, 0xce, 0x27, 0x02, 0xc2, 0x27,
	0xd0, 0x96, 0x45, 0x55, 0x72, 0xba, 0xbc, 0x04, 0xcf, 0x0f, 0x88, 0x21, 0xe1, 0x82, 0x36, 0xab,
	0x6a, 0x8b, 0x27, 0xd2, 0x31, 0x91, 0x28, 0x89, 0xaf, 0xef, 0x79, 0x47, 0x9e, 0xc2, 0xb2, 0x10,
	0xf9, 0xda, 0xc1, 0x0e, 0x1c, 0xed, 0x55, 0xbe, 0x97, 0x44, 0x34, 0xa0, 0x66, 0x8f, 0x1f, 0x3e,
	0x56, 0x75, 0x95, 0xf4, 0x76, 0x7b, 0xe0, 0x8a, 0x6f, 0xe2, 0xef, 0xe0, 0xa3, 0x5b, 0x8d, 0x50,
	0xa8, 0x30, 0x6f, 0x07, 0xa1, 0x3a, 0xda, 0x6f, 0x09, 0xa9, 0xfb, 0xb3, 0xec, 0xdf, 0x30, 0x65,
	0x9b, 0xe4, 0x55, 0x44, 0x57, 0x4b, 0x33, 0xb0, 0x14, 0x1b, 0x9c, 0xd1, 0x9d, 0xf1, 0xcf, 0x85,
	0xf0, 0x8c, 0x0b, 0x7f, 0xe2, 0x42, 0xf2, 0x8e, 0xd5, 0xf1, 0xdf, 0x2a, 0xe8, 0x65, 0x2a, 0xff,
	0x77, 0x56, 0x04, 0x0f, 0x99, 0x15, 0xcb, 0xf7, 0xcd, 0x8a, 0xc5, 0x7e, 0x4f, 0x52, 0x91, 0x80,
	0x6f, 0xef, 0x5d, 0x64, 0xbb, 0x99, 0xd8, 0xe9, 0xd1, 0xfe, 0x02, 0x60, 0xbb, 0x83, 0x5b, 0xd0,
	0x58, 0xcc, 0x7e, 0x9e, 0xfd, 0xf2, 0xfb, 0x0c, 0x1d, 0x60, 0x04, 0x86, 0x58, 0x7a, 0xe2, 0x77,
	0x8e, 0x14, 0x8c, 0xa1, 0x23, 0x91, 0x82, 0x34, 0x47, 0x2a, 0x7e, 0x04, 0xa8, 0xf8, 0xda, 0xa2,
	0xda, 0xf1, 0x73, 0x30, 0x76, 0x0b, 0x12, 0x23, 0xd0, 0xfe, 0xa2, 0x37, 0xc5, 0xd8, 0xe4, 0x4b,
	0xfc, 0x08, 0x6a, 0x6f, 0xfc, 0xd5, 0x86, 0x4f, 0x47, 0x8e, 0xc9, 0x8f, 0xe7, 0xea, 0xf7, 0xca,
	0xf1, 0x29, 0xa0, 0xdb, 0xcf, 0x8e, 0x3f, 0x05, 0x3d, 0x8a, 0x3d, 0xf1, 0xea, 0xe6, 0x52, 0x08,
	0x1a, 0x51, 0x2c, 0xf6, 0xfb, 0xcf, 0xde, 0x7f, 0x83, 0x06, 0x68, 0xc4, 0xfd, 0x11, 0x29, 0xb8,
	0x09, 0xb5, 0x33, 0xe2, 0xba, 0x33, 0xa4, 0x62, 0x1d, 0x0e, 0xc7, 0x97, 0x0b, 0x17, 0x69, 0x63,
	0x03, 0x40, 0x74, 0x19, 0x8b, 0x29, 0x7b, 0xd5, 0x7f, 0x0b, 0xbd, 0xb7, 0x34, 0x4a, 0xcb, 0x1e,
	0xf4, 0x62, 0x7f, 0x4d, 0x1f, 0x32, 0xfa, 0x9f, 0xc1, 0x27, 0xe4, 0x9d, 0x74, 0x4e, 0xd8, 0x26,
	0xce, 0xc5, 0x54, 0xaf, 0x91, 0x8f, 0xc9, 0xed, 0x9c, 0x8a, 0xdd, 0xa7, 0x3f, 0x40, 0x5d, 0x8e,
	0xbc, 0xfd, 0xd8, 0x0d, 0xd0, 0xaf, 0xc9, 0xc5, 0x8b, 0xd9, 0xd9, 0xa5, 0x8b, 0x34, 0x0c, 0x50,
	0x9f, 0xff, 0xba, 0x78, 0x41, 0x5c, 0x74, 0x88, 0x5b, 0x50, 0x9f, 0x5c, 0x90, 0xc9, 0xa5, 0x8b,
	0xfe, 0x69, 0x3c, 0x3d, 0x81, 0x8e, 0x08, 0x9b, 0x5f, 0x5e, 0xc6, 0x7c, 0x04, 0x5d, 0x81, 0xfc,
	0xc6, 0x1f, 0x54, 0x40, 0xe8, 0x60, 0xdc, 0xfb, 0xa3, 0x5b, 0xfd, 0xfb, 0x79, 0x62, 0xf5, 0x5f,
	0x00, 0x00, 0x00, 0xff, 0xff, 0xaf, 0xce, 0xda, 0xd4, 0x2a, 0x07, 0x00, 0x00,
}
