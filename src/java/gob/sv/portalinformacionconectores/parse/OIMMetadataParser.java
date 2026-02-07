/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sv.portalinformacionconectores.parse;

import gob.sv.portalinformacionconectores.data.AttributeMapping;
import gob.sv.portalinformacionconectores.data.CorrelationRule;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class OIMMetadataParser {

    private Document doc;
    private XPath xpath;

    public OIMMetadataParser(String xmlContent) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        this.doc = builder.parse(new InputSource(new StringReader(xmlContent)));
        this.xpath = XPathFactory.newInstance().newXPath();
    }

    public String getConnectorDeploymentPath() throws Exception {
        return xpath.evaluate("/application/connectorDeploymentPath", doc);
    }

    public String getTransformationScript() throws Exception {
        return xpath.evaluate("//transformationScript", doc);
    }

    public List<AttributeMapping> getMappings() throws Exception {
        List<AttributeMapping> mappings = new ArrayList<>();

        NodeList nodes = (NodeList) xpath.evaluate("//schemaAttribute", doc, XPathConstants.NODESET);

        for (int i = 0; i < nodes.getLength(); i++) {
            Element el = (Element) nodes.item(i);

            String target = el.getAttribute("name");

            String origin = el.getAttribute("identityAttribute");

            String display = el.getAttribute("displayName");
            
            String dataType = el.getAttribute("dataType");

            mappings.add(new AttributeMapping(target, origin, display, dataType));
        }
        return mappings;
    }

    public List<CorrelationRule> getIdentityCorrelationRules() throws Exception {
        List<CorrelationRule> rules = new ArrayList<>();

        String globalLogic = xpath.evaluate("//identityCorrelationRule/@ruleOperator", doc);
        if (globalLogic == null || globalLogic.isEmpty()) {
            globalLogic = "N/A";
        }

        NodeList ruleNodes = (NodeList) xpath.evaluate("//ruleElement", doc, XPathConstants.NODESET);

        for (int i = 0; i < ruleNodes.getLength(); i++) {
            Element el = (Element) ruleNodes.item(i);

            rules.add(new CorrelationRule(
                    el.getAttribute("targetAttribute"),
                    el.getAttribute("userAttribute"),
                    el.getAttribute("elementOperator"),
                    globalLogic
            ));
        }
        return rules;
    }

}
