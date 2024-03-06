// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.intellij.terraform.config.codeinsight;

import com.intellij.openapi.util.registry.Registry;
import org.intellij.terraform.config.model.*;

import java.util.*;
import java.util.function.Predicate;

import static org.assertj.core.api.BDDAssertions.then;
import static org.intellij.terraform.config.CompletionTestCase.Matcher.*;

@SuppressWarnings({"ArraysAsListWithZeroOrOneArgument", "RedundantThrows"})
public class TerraformConfigCompletionTest extends TFBaseCompletionTestCase {

  public void testBlockKeywordCompletion() throws Exception {
    doBasicCompletionTest("<caret> {}", TerraformCompletionUtil.INSTANCE.getRootBlockKeywords());
    doBasicCompletionTest("a=1\n<caret> {}", TerraformCompletionUtil.INSTANCE.getRootBlockKeywords());

    doBasicCompletionTest("<caret> ", TerraformCompletionUtil.INSTANCE.getRootBlockKeywords());
    doBasicCompletionTest("a=1\n<caret> ", TerraformCompletionUtil.INSTANCE.getRootBlockKeywords());

    doBasicCompletionTest("\"<caret>\" {}", TerraformCompletionUtil.INSTANCE.getRootBlockKeywords());
    doBasicCompletionTest("\"<caret> {}", TerraformCompletionUtil.INSTANCE.getRootBlockKeywords());
    doBasicCompletionTest("a=1\n\"<caret>\" {}", TerraformCompletionUtil.INSTANCE.getRootBlockKeywords());
    doBasicCompletionTest("a=1\n\"<caret> {}", TerraformCompletionUtil.INSTANCE.getRootBlockKeywords());

    doBasicCompletionTest("\"<caret>\" ", TerraformCompletionUtil.INSTANCE.getRootBlockKeywords());
    doBasicCompletionTest("\"<caret> ", TerraformCompletionUtil.INSTANCE.getRootBlockKeywords());
    doBasicCompletionTest("a=1\n\"<caret>\" ", TerraformCompletionUtil.INSTANCE.getRootBlockKeywords());
    doBasicCompletionTest("a=1\n\"<caret> ", TerraformCompletionUtil.INSTANCE.getRootBlockKeywords());
  }

  public void testNoBlockKeywordCompletion() throws Exception {
    doBasicCompletionTest("a={\n<caret>\n}", 0);
  }

  //<editor-fold desc="Resources completion tests">
  public void testResourceTypeCompletion() throws Exception {
    final TreeSet<String> set = new TreeSet<>();
    for (ResourceType resource : TypeModelProvider.Companion.getGlobalModel().getResources()) {
      set.add(resource.getType());
    }
    final Predicate<Collection<String>> matcher = getPartialMatcher(new ArrayList<>(set).subList(0, 500));
    doBasicCompletionTest("resource <caret>", matcher);
    doBasicCompletionTest("resource <caret> {}", matcher);
    doBasicCompletionTest("resource <caret> \"aaa\" {}", matcher);
    doBasicCompletionTest("\"resource\" <caret>", matcher);
    doBasicCompletionTest("\"resource\" <caret> {}", matcher);
    doBasicCompletionTest("\"resource\" <caret> \"aaa\" {}", matcher);
  }

  public void testResourceQuotedTypeCompletion() throws Exception {
    final TreeSet<String> set = new TreeSet<>();
    for (ResourceType resource : TypeModelProvider.Companion.getGlobalModel().getResources()) {
      set.add(resource.getType());
    }
    final Predicate<Collection<String>> matcher = getPartialMatcher(new ArrayList<>(set).subList(0, 500));
    doBasicCompletionTest("resource \"<caret>", matcher);
    doBasicCompletionTest("resource '<caret>", matcher);
    doBasicCompletionTest("resource \"<caret>\n{}", matcher);
    doBasicCompletionTest("resource '<caret>\n{}", matcher);
    doBasicCompletionTest("resource \"<caret>\" {}", matcher);
    doBasicCompletionTest("resource \"<caret>\" \"aaa\" {}", matcher);
    doBasicCompletionTest("\"resource\" \"<caret>", matcher);
    doBasicCompletionTest("\"resource\" '<caret>", matcher);
    doBasicCompletionTest("\"resource\" \"<caret>\n{}", matcher);
    doBasicCompletionTest("\"resource\" '<caret>\n{}", matcher);
    doBasicCompletionTest("\"resource\" \"<caret>\" {}", matcher);
    doBasicCompletionTest("\"resource\" \"<caret>\" \"aaa\" {}", matcher);
  }

  public void testResourceCommonPropertyCompletion() throws Exception {
    doBasicCompletionTest("resource abc {\n<caret>\n}", COMMON_RESOURCE_PROPERTIES);
    final HashSet<String> set = new HashSet<>(COMMON_RESOURCE_PROPERTIES);
    set.remove("id");
    doBasicCompletionTest("resource \"x\" {\nid='a'\n<caret>\n}", set);
    doBasicCompletionTest("resource abc {\n<caret> = true\n}", Collections.emptySet());
    doBasicCompletionTest("resource abc {\n<caret> {}\n}", Arrays.asList("lifecycle", "connection", "provisioner", "dynamic"));
    doBasicCompletionTest("resource abc {\n<caret>count = 2\n}", 1, "count");
    doBasicCompletionTest("resource abc {\n\"<caret>count\" = 2\n}", 1, "count");
    // TODO: Fix mixed id-string and uncomment next line
    //doBasicCompletionTest("resource abc {\n<caret>\"count\" = 2\n}", 1, "count");
    doBasicCompletionTest("resource abc {\n<caret>lifecycle {}\n}", Arrays.asList("lifecycle", "connection", "provisioner", "dynamic"));
  }

  public void testResourceDynamicCompletion() throws Exception {
    doBasicCompletionTest("resource abc {\n dynamic x {<caret>}\n}", "for_each", "labels", "iterator", "content");
    doBasicCompletionTest("resource abc {\n dynamic <caret> \n}", not("lifecycle", "provisioner", "dynamic"));

    //    doBasicCompletionTest("resource abc {\n dynamic <caret> {}\n}", not("lifecycle", "provisioner", "dynamic"));
  }

  public void testResourceForEachCompletion() throws Exception {
    doBasicCompletionTest("resource 'null_resource' 'x' { id = <caret>}", not("each"));
    doBasicCompletionTest("resource 'null_resource' 'x' { for_each={}\n id = <caret>}", "each");
    doBasicCompletionTest("resource 'null_resource' 'x' { for_each={}\n id = each.<caret>}", 2, "key", "value");
  }

  public void testResourceEachValueCompletion() throws Exception {
    doBasicCompletionTest("""
                            resource "aws_instance" "resource-name-test0" {
                              for_each = {"vm1" = { type = "t2.micro", ami = "ami-052efd3df9dad4825", name = "resource-terraform-test0" }}
                              ami           = each.value.ami
                              instance_type = each.value.<caret>
                              tags = {
                                Name = each.value.name
                              }
                            }
                            """, 3, "ami", "name", "type");

    doBasicCompletionTest("""
                            variable "servers" {
                              type = map(object({
                                instance_type = string
                                ami           = string
                              }))
                              default = {
                                web = {
                                  instance_type = "t2.micro"
                                  ami           = "ami-12345678"
                                }
                                app = {
                                  instance_type = "t2.medium"
                                  ami           = "ami-87654321"
                                }
                              }
                            }
                                                        
                            resource "aws_instance" "example" {
                              for_each = var.servers
                                                        
                              ami           = each.value.<caret>
                              instance_type = each.value.instance_type
                            }
                            """, 2, "ami", "instance_type");
  }

  public void testResourceCommonPropertyCompletionFromModel() throws Exception {
    final HashSet<String> base = new HashSet<>(COMMON_RESOURCE_PROPERTIES);
    final ResourceType type = TypeModelProvider.Companion.getGlobalModel().getResourceType("aws_instance");
    assertNotNull(type);
    for (PropertyOrBlockType it : type.getProperties().values()) {
      if (it.getConfigurable()) base.add(it.getName());
    }
    doBasicCompletionTest("resource aws_instance x {\n<caret>\n}", base);
    doBasicCompletionTest("resource aws_instance x {\n<caret> = \"name\"\n}", "provider", "ami");
    doBasicCompletionTest("resource aws_instance x {\n<caret> = true\n}", "ebs_optimized", "monitoring");
    doBasicCompletionTest("resource aws_instance x {\n<caret> {}\n}", "lifecycle");

    doBasicCompletionTest("resource aws_instance x {\n\"<caret>\"\n}", base);
    doBasicCompletionTest("resource aws_instance x {\n\"<caret>\" = \"name\"\n}", "provider", "ami");
    doBasicCompletionTest("resource aws_instance x {\n\"<caret>\" = true\n}", "ebs_optimized", "monitoring");
    doBasicCompletionTest("resource aws_instance x {\n\"<caret>\" {}\n}", "lifecycle");

    // Should understand interpolation result
    doBasicCompletionTest("resource aws_instance x {\n<caret> = \"${true}\"\n}", strings -> {
      then(strings).contains("ebs_optimized", "monitoring").doesNotContain("lifecycle", "provider", "ami");
      return true;
    });
    // Or not
    doBasicCompletionTest("resource aws_instance x {\n<caret> = \"${}\"\n}", strings -> {
      then(strings).contains("ebs_optimized", "monitoring", "provider", "ami").doesNotContain("lifecycle");
      return true;
    });
  }

  public void testResourceCommonPropertyAlreadyDefinedNotShownAgain() throws Exception {
    final ResourceType type = TypeModelProvider.Companion.getGlobalModel().getResourceType("aws_vpc_endpoint");
    assertNotNull(type);

    // Should not add existing props to completion variants
    doBasicCompletionTest("""
                            resource aws_vpc_endpoint x {
                              <caret>  service_name = ""
                              vpc_id = ""
                            }
                            """, not("service_name", "vpc_id"));
    doBasicCompletionTest("""
                            resource aws_vpc_endpoint x {
                              service_name = ""
                              <caret>  vpc_id = ""
                            }
                            """, not("service_name", "vpc_id"));
    doBasicCompletionTest("""
                            resource aws_vpc_endpoint x {
                              service_name = ""
                              vpc_id = ""
                              <caret>}
                            """, not("service_name", "vpc_id"));

    // yet should advice if we stand on it
    doBasicCompletionTest("""
                            resource aws_vpc_endpoint x {
                              service_name = ""
                              <caret>vpc_id = ""
                            }
                            """, and(not("service_name"), all("vpc_id")));
    doBasicCompletionTest("""
                            resource aws_vpc_endpoint x {
                              <caret>service_name = ""
                              vpc_id = ""
                            }
                            """, and(not("vpc_id"), all("service_name")));
  }

  public void testResourceProviderCompletionFromModel() throws Exception {
    doBasicCompletionTest("provider Z {}\nresource a b {provider=<caret>}", "Z");
    doBasicCompletionTest("provider Z {}\nresource a b {provider='<caret>'}", "Z");
    doBasicCompletionTest("provider Z {}\nresource a b {provider=\"<caret>\"}", "Z");
    doBasicCompletionTest("provider Z {alias='Y'}\nresource a b {provider=<caret>}", "Z.Y");
    doBasicCompletionTest("provider Z {alias='Y'}\nresource a b {provider='<caret>'}", "Z.Y");
    doBasicCompletionTest("provider Z {alias='Y'}\nresource a b {provider=\"<caret>\"}", "Z.Y");
  }

  public void testResourcePropertyCompletionBeforeInnerBlock() throws Exception {
    doBasicCompletionTest("resource abc {\n<caret>\nlifecycle {}\n}", COMMON_RESOURCE_PROPERTIES);
    final HashSet<String> set = new HashSet<>(COMMON_RESOURCE_PROPERTIES);
    set.remove("id");
    doBasicCompletionTest("resource \"x\" {\nid='a'\n<caret>\nlifecycle {}\n}", set);
    doBasicCompletionTest("resource abc {\n<caret> = true\nlifecycle {}\n}", Collections.emptySet());
  }

  public void testResourceDependsOnCompletion() throws Exception {
    doBasicCompletionTest("resource x y {}\nresource a b {depends_on=['<caret>']}", 1, "x.y");
    doBasicCompletionTest("resource x y {}\nresource a b {depends_on=[\"<caret>\"]}", 1, "x.y");
    doBasicCompletionTest("data x y {}\nresource a b {depends_on=['<caret>']}", 1, "data.x.y");
    doBasicCompletionTest("data x y {}\nresource a b {depends_on=[\"<caret>\"]}", 1, "data.x.y");

    doBasicCompletionTest("resource x y {}\nresource a b {depends_on=[<caret>]}", 1, "x.y");
    doBasicCompletionTest("data x y {}\nresource a b {depends_on=[<caret>]}", 1, "data.x.y");

    doBasicCompletionTest("variable v{}\nresource x y {}\nresource a b {depends_on=[<caret>]}", 2, "x.y", "var.v");
    doBasicCompletionTest("variable v{}\ndata x y {}\nresource a b {depends_on=[<caret>]}", 2, "data.x.y", "var.v");
  }

  public void testResourceTypeCompletionGivenDefinedProvidersOrForNoPropsProviders() throws Exception {
    Registry.get("ide.completion.variant.limit").setValue(2000, getTestRootDisposable());

    final TreeSet<String> set = new TreeSet<>();
    final Map<String, Boolean> cache = new HashMap<>();
    for (ResourceType resource : TypeModelProvider.Companion.getGlobalModel().getResources()) {
      if (isExcludeProvider(resource.getProvider(), cache)) continue;
      set.add(resource.getType());
    }
    then(set).contains("template_file", "aws_vpc");
    doBasicCompletionTest("provider aws {}\nresource <caret>", set);
    doBasicCompletionTest("provider aws {}\nresource <caret> {}", set);
    doBasicCompletionTest("provider aws {}\nresource <caret> \"aaa\" {}", set);
  }

  public void testResourceNonConfigurablePropertyIsNotAdviced() throws Exception {
    doBasicCompletionTest("resource \"random_string\" \"x\" { <caret> }", strings -> {
      then(strings).doesNotContain("result");
      return true;
    });
  }
  //</editor-fold>

  //<editor-fold desc="Data Sources completion tests">
  public void testDataSourceTypeCompletion() throws Exception {
    Registry.get("ide.completion.variant.limit").setValue(100000, getTestRootDisposable());
    final TreeSet<String> set = new TreeSet<>();
    for (DataSourceType ds : TypeModelProvider.Companion.getGlobalModel().getDataSources()) {
      set.add(ds.getType());
    }
    doBasicCompletionTest("data <caret>", set);
    doBasicCompletionTest("data <caret> {}", set);
    doBasicCompletionTest("data <caret> \"aaa\" {}", set);
  }

  public void testCheckBlockCompletion() throws Exception {
    doBasicCompletionTest("check {<caret>}", "assert", "data");
    doBasicCompletionTest(
      """
        check "certificate" {
          assert {
            condition     = aws_acm_certificate.cert.status == "ERRORED"
            error_message = "Certificate status is ${aws_acm_certificate.cert.status}"
          }
          data abc {
            <caret>
          }"
        }""", COMMON_DATA_SOURCE_PROPERTIES);
    doTheOnlyVariantCompletionTest(
      """
        check "certificate" {
          dat<caret>
        }""",
      """
        check "certificate" {
          data "" "" {}
        }""", false
    );
  }

  public void testRemovedBlockCompletion() throws Exception {
    doBasicCompletionTest("removed {<caret>}", 2, "from", "lifecycle");
    doBasicCompletionTest(
      """
        removed {
          from = test
          lifecycle {
            <caret>
          }
        }""", 6, "postcondition", "precondition", "create_before_destroy", "ignore_changes",
      "prevent_destroy", "replace_triggered_by");
  }

  public void testDataSourceQuotedTypeCompletion() throws Exception {
    Registry.get("ide.completion.variant.limit").setValue(100000, getTestRootDisposable());
    final TreeSet<String> set = new TreeSet<>();
    for (DataSourceType ds : TypeModelProvider.Companion.getGlobalModel().getDataSources()) {
      set.add(ds.getType());
    }
    doBasicCompletionTest("data \"<caret>", set);
    doBasicCompletionTest("data '<caret>", set);
    doBasicCompletionTest("data \"<caret>\n{}", set);
    doBasicCompletionTest("data '<caret>\n{}", set);
    doBasicCompletionTest("data \"<caret>\" {}", set);
    doBasicCompletionTest("data \"<caret>\" \"aaa\" {}", set);
  }

  public void testDataSourceCommonPropertyCompletion() throws Exception {
    doBasicCompletionTest("data abc {\n<caret>\n}", COMMON_DATA_SOURCE_PROPERTIES);
    final HashSet<String> set = new HashSet<>(COMMON_DATA_SOURCE_PROPERTIES);
    set.remove("id");
    doBasicCompletionTest("data \"x\" {\nid='a'\n<caret>\n}", set);
    doBasicCompletionTest("data abc {\n<caret> = true\n}", Collections.emptySet());
    // lifecycle block for DataSource, that's why size=1
    doBasicCompletionTest("data abc {\n<caret> {}\n}", 1, "lifecycle");
  }

  public void testDataSourceCommonPropertyCompletionFromModel() throws Exception {
    final HashSet<String> base = new HashSet<>(COMMON_DATA_SOURCE_PROPERTIES);
    final DataSourceType type = TypeModelProvider.Companion.getGlobalModel().getDataSourceType("aws_ecs_container_definition");
    assertNotNull(type);
    for (PropertyOrBlockType it : type.getProperties().values()) {
      if (it.getConfigurable()) base.add(it.getName());
    }
    doBasicCompletionTest("data aws_ecs_container_definition x {\n<caret>\n}", base);
    doBasicCompletionTest("data aws_ecs_container_definition x {\n<caret> = \"name\"\n}",
                          "container_name",
                          "task_definition",
                          "provider"
    );
    doBasicCompletionTest("data aws_elastic_beanstalk_solution_stack x {\n<caret> = true\n}", "most_recent");
    doBasicCompletionTest("data aws_kms_secret x {\n<caret> {}\n}", "secret");

    // Should understand interpolation result
    doBasicCompletionTest("data aws_elastic_beanstalk_solution_stack x {\n<caret> = \"${true}\"\n}", strings -> {
      then(strings).contains("most_recent").doesNotContain("name", "name_regex");
      return true;
    });
  }

  public void testDataSourceProviderCompletionFromModel() throws Exception {
    doBasicCompletionTest("provider Z {}\ndata a b {provider=<caret>}", "Z");
    doBasicCompletionTest("provider Z {}\ndata a b {provider='<caret>'}", "Z");
    doBasicCompletionTest("provider Z {}\ndata a b {provider=\"<caret>\"}", "Z");
    doBasicCompletionTest("provider Z {alias='Y'}\ndata a b {provider=<caret>}", "Z.Y");
    doBasicCompletionTest("provider Z {alias='Y'}\ndata a b {provider='<caret>'}", "Z.Y");
    doBasicCompletionTest("provider Z {alias='Y'}\ndata a b {provider=\"<caret>\"}", "Z.Y");
  }

  public void testDataSourceDependsOnCompletion() throws Exception {
    doBasicCompletionTest("resource x y {}\ndata a b {depends_on=['<caret>']}", 1, "x.y");
    doBasicCompletionTest("resource x y {}\ndata a b {depends_on=[\"<caret>\"]}", 1, "x.y");
    doBasicCompletionTest("data x y {}\ndata a b {depends_on=['<caret>']}", 1, "data.x.y");
    doBasicCompletionTest("data x y {}\ndata a b {depends_on=[\"<caret>\"]}", 1, "data.x.y");

    doBasicCompletionTest("resource x y {}\ndata a b {depends_on=[<caret>]}", 1, "x.y");
    doBasicCompletionTest("data x y {}\ndata a b {depends_on=[<caret>]}", 1, "data.x.y");

    doBasicCompletionTest("variable v{}\nresource x y {}\ndata a b {depends_on=[<caret>]}", 2, "x.y", "var.v");
    doBasicCompletionTest("variable v{}\ndata x y {}\ndata a b {depends_on=[<caret>]}", 2, "data.x.y", "var.v");
  }

  public void testDataSourceTypeCompletionGivenDefinedProviders() throws Exception {
    final TreeSet<String> set = new TreeSet<>();
    final Map<String, Boolean> cache = new HashMap<>();
    for (DataSourceType ds : TypeModelProvider.Companion.getGlobalModel().getDataSources()) {
      if (isExcludeProvider(ds.getProvider(), cache)) continue;
      set.add(ds.getType());
    }
    then(set).contains("template_file", "aws_vpc");
    doBasicCompletionTest("provider aws {}\ndata <caret>", set);
    doBasicCompletionTest("provider aws {}\ndata <caret> {}", set);
    doBasicCompletionTest("provider aws {}\ndata <caret> \"aaa\" {}", set);
  }
  //</editor-fold>

  public void testOutputBasicCompletion() throws Exception {
    doBasicCompletionTest("output test1 {<caret>}", 5, "description");
    doBasicCompletionTest("output test2 {\np<caret>}", 3, "precondition", "description", "depends_on");
  }

  public void testVariableBasicCompletion() throws Exception {
    doBasicCompletionTest("variable test1 {\n<caret>}", 6, "type");
    doBasicCompletionTest("variable test2 {\ns<caret>}", 2, "sensitive", "description");
    doBasicCompletionTest("variable test3 {\nn<caret>}", 4, "nullable", "validation");
    doBasicCompletionTest("variable test4 {\nd<caret>}", 3, "default");
  }

  public void testLifecycleBasicCompletion() throws Exception {
    doBasicCompletionTest("""
                            resource null_resource test {
                              lifecycle {
                                con<caret>
                              }
                            }
                            """.trim(), 2, "precondition", "postcondition");

    doBasicCompletionTest("""
                            data "abbey_identity" "test" {
                              id = ""
                              lifecycle {
                                <caret>
                              }
                            }
                            """.trim(), 6, "replace_triggered_by");

    doBasicCompletionTest("""
                            resource null_resource test {
                              lifecycle {
                                create_before_destroy = f<caret>
                              }
                            }
                            """.trim(), "false");
  }

  public void testOutputDependsOnCompletion() throws Exception {
    doBasicCompletionTest("output o {<caret>}", "depends_on");

    doBasicCompletionTest("resource x y {}\noutput o {depends_on=[<caret>]}", 1, "x.y");
    doBasicCompletionTest("resource x y {}\noutput o {depends_on=['<caret>']}", 1, "x.y");
    doBasicCompletionTest("resource x y {}\noutput o {depends_on=[\"<caret>\"]}", 1, "x.y");
    doBasicCompletionTest("data x y {}\noutput o {depends_on=[<caret>]}", 1, "data.x.y");
    doBasicCompletionTest("data x y {}\noutput o {depends_on=['<caret>']}", 1, "data.x.y");
    doBasicCompletionTest("data x y {}\noutput o {depends_on=[\"<caret>\"]}", 1, "data.x.y");

    doBasicCompletionTest("variable v{}\nresource x y {}\noutput o {depends_on=[<caret>]}", 2, "x.y", "var.v");
    doBasicCompletionTest("variable v{}\ndata x y {}\noutput o {depends_on=[<caret>]}", 2, "data.x.y", "var.v");
  }

  public void testVariableTypeCompletion() throws Exception {
    myCompleteInvocationCount = 1; // Ensure there would not be 'null', 'true' and 'false' variants
    doBasicCompletionTest("variable v { type = <caret> }", 9,
                          "any", "string", "number", "bool", "list", "set", "map", "object", "tuple");
    doBasicCompletionTest("variable v { type = object(x=<caret>) }", 10,
                          "any", "string", "number", "bool", "list", "set", "map", "object", "tuple", "optional");
    doBasicCompletionTest("variable v { type = object(x=optional(<caret>)) }", 9,
                          "any", "string", "number", "bool", "list", "set", "map", "object", "tuple");
    doBasicCompletionTest("variable v { type = object(x=list(<caret>)) }", 9,
                          "any", "string", "number", "bool", "list", "set", "map", "object", "tuple");

    doBasicCompletionTest("variable v { type = object(x=<caret>optional()) }", 10,
                          "any", "string", "number", "bool", "list", "set", "map", "object", "tuple", "optional");
  }

  public void testSpecial_HasDynamicAttributes_Property_Not_Advised() throws Exception {
    doBasicCompletionTest("data \"terraform_remote_state\" \"x\" { <caret> }", strings -> {
      then(strings).contains("backend").doesNotContain("__has_dynamic_attributes");
      return true;
    });
  }

  public void testModuleProvidersPropertyCompletion() {
    myFixture.addFileToProject("module/a.tf", "provider aws {}\nprovider aws {alias=\"second\"}");
    // via PropertyObjectKeyCompletionProvider
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers = {
                                <caret>\s
                              }
                            }""", Arrays.asList("aws", "aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers = {
                                "<caret>"\s
                              }
                            }""", Arrays.asList("aws", "aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers = {
                                a<caret>\s
                              }
                            }""", Arrays.asList("aws", "aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers = {
                                "a<caret>"\s
                              }
                            }""", Arrays.asList("aws", "aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers = {
                                <caret>aws = "aws"\s
                              }
                            }""", Arrays.asList("aws", "aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers = {
                                "<caret>aws" = "aws"\s
                              }
                            }""", Arrays.asList("aws", "aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers = {
                                <caret> = "aws"\s
                              }
                            }""", Arrays.asList("aws", "aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers = {
                                "<caret>" = "aws"\s
                              }
                            }""", Arrays.asList("aws", "aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers = {
                                aws = "aws"
                                <caret>
                              }
                            }""", Arrays.asList("aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers = {
                                <caret>
                                aws = "aws"\s
                              }
                            }""", Arrays.asList("aws.second"));


    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers = {
                                aws = <caret>\s
                              }
                            }""", 0);
  }

  public void testModuleProvidersBlockCompletion() {
    myFixture.addFileToProject("module/a.tf", "provider aws {}\nprovider aws {alias=\"second\"}");
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers {
                                <caret>\s
                              }
                            }""", Arrays.asList("aws", "aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers {
                                "<caret>"\s
                              }
                            }""", Arrays.asList("aws", "aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers {
                                a<caret>\s
                              }
                            }""", Arrays.asList("aws", "aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers {
                                "a<caret>"\s
                              }
                            }""", Arrays.asList("aws", "aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers {
                                <caret>aws = "aws"\s
                              }
                            }""", Arrays.asList("aws", "aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers {
                                "<caret>aws" = "aws"\s
                              }
                            }""", Arrays.asList("aws", "aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers {
                                <caret> = "aws"\s
                              }
                            }""", Arrays.asList("aws", "aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers {
                                "<caret>" = "aws"\s
                              }
                            }""", Arrays.asList("aws", "aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers {
                                aws = "aws"
                                <caret>
                              }
                            }""", Arrays.asList("aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers {
                                <caret>
                                aws = "aws"\s
                              }
                            }""", Arrays.asList("aws.second"));
    doBasicCompletionTest("""
                            module x {
                              source = "./module/"
                              providers {
                                aws = <caret>\s
                              }
                            }""", 0);
    doBasicCompletionTest("""
                            variable "obj-var" {
                               type = object({
                                 var1 = string
                                 var2 = list(string)
                                 var3 = object({
                                   var4 = object({
                                     var6 = list(any)
                                     var7 = list(bool)
                                     var8 = list(number)
                                   })
                                 })
                               })
                             }
                             
                             module "test" {
                               source = "./"
                             
                               obj-var = {
                                 var1 = ""
                                 var2 = []
                                 var3 = {
                                   var4 = {
                                    <caret>
                                   }
                                 }
                               }
                             }
                            """, Arrays.asList("var6", "var7", "var8"));
  }

  public void testModuleProvidersValueCompletion() {
    myFixture.addFileToProject("module/a.tf", "provider aws {}\nprovider aws {alias=\"second\"}");
    // via PropertyObjectKeyCompletionProvider
    doBasicCompletionTest("""
                            provider aws {}module x {
                              source = "./module/"
                              providers = {
                                aws=<caret>\s
                              }
                            }""", Arrays.asList("aws"));
    doBasicCompletionTest("""
                            provider aws {alias="first"}module x {
                              source = "./module/"
                              providers {
                                aws=<caret>\s
                              }
                            }""", Arrays.asList("aws.first"));
    doBasicCompletionTest("""
                            provider aws {alias="first"}module x {
                              source = "./module/"
                              providers {
                                aws="<caret>"\s
                              }
                            }""", Arrays.asList("aws.first"));
  }

  public void testModuleForEachCompletion() throws Exception {
    doBasicCompletionTest("module 'x' { id = <caret>}", not("each"));
    doBasicCompletionTest("module 'x' { for_each={}\n id = <caret>}", "each");
    doBasicCompletionTest("module 'x' { for_each={}\n id = each.<caret>}", 2, "key", "value");
  }


  public void testModuleDependsOnCompletion() throws Exception {
    doBasicCompletionTest("resource x y {}\nmodule b {depends_on=['<caret>']}", 1, "x.y");
    doBasicCompletionTest("resource x y {}\nmodule b {depends_on=[\"<caret>\"]}", 1, "x.y");
    doBasicCompletionTest("data x y {}\nmodule b {depends_on=['<caret>']}", 1, "data.x.y");
    doBasicCompletionTest("data x y {}\nmodule b {depends_on=[\"<caret>\"]}", 1, "data.x.y");

    doBasicCompletionTest("resource x y {}\nmodule b {depends_on=[<caret>]}", 1, "x.y");
    doBasicCompletionTest("data x y {}\nmodule b {depends_on=[<caret>]}", 1, "data.x.y");

    doBasicCompletionTest("variable v{}\nresource x y {}\nmodule b {depends_on=[<caret>]}", 2, "x.y", "var.v");
    doBasicCompletionTest("variable v{}\ndata x y {}\nmodule b {depends_on=[<caret>]}", 2, "data.x.y", "var.v");
  }

  public void testCompleteResourceFromAnotherModuleInImportBlock() {
    myFixture.addFileToProject("submodule/sub.tf", """
      resource "MyType" "MyName" {}
      """);
    myFixture.configureByText("main.tf", """
      import {
        id = "terraform"
        to = module.submodule.<caret>
      }
            
      module "submodule" {
        source = "./submodule"
      }
      """);
    myFixture.testCompletionVariants("main.tf", "MyType.MyName");
  }

  public void testCompleteResourceFromMovedBlock() {
    myFixture.addFileToProject("modules/compute/main.tf", """ 
      resource "aws_instance" "example1" { }
            
      resource "aws_instance" "example" { }
      """);
    myFixture.configureByText("main.tf", """
      module "ec2_instance" {
        source         = "./modules/compute"
        security_group = module.web_security_group.security_group_id
        public_subnets = module.vpc.public_subnets
      }
            
      moved {
        from = aws_instance.example
        to = module.ec2_instance.aws<caret>
      }
      """);
    myFixture.testCompletionVariants("main.tf", "aws_instance.example", "aws_instance.example1");
  }


  private static boolean isExcludeProvider(ProviderType provider, Map<String, Boolean> cache) {
    String key = provider.getType();
    Boolean cached = cache.get(key);
    if (cached == null) {
      cached = true;
      if (provider.getType().equals("aws")) {
        cached = false;
      }
      else if (provider.getProperties().equals(TypeModel.AbstractProvider.getProperties())) {
        cached = false;
      }
      cache.put(key, cached);
    }
    return cached;
  }
}
