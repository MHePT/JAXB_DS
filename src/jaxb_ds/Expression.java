package JAXB_DS; 

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;

// the first tag in the XML called Root element it's name in the file is expr <expr></expr>
@XmlRootElement(name = "expr")
public class Expression {

    private String type;
    private Operator operator;
    private Atom atom;
    private List<Expression> expressions;

    
    public String getType() {
        return type;
    }

    //there is an Atrribute called type in expr tag <expr type="binary"></expr>
    @XmlAttribute
    public void setType(String type) {
        this.type = type;
    }

    public Operator getOperator() {
        return operator;
    }

    //there is an Element called operator in expr tag <expr type="binary"><operator/></expr>
    @XmlElement (name = "operator")
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
    //there is an Element called atom in expr tag <expr type="binary"><atom/></expr>
    @XmlElement (name = "atom")
    public Atom getAtom() {
        return atom;
    }

    public void setAtom(Atom atom) {
        this.atom = atom;
    }

    //there is other expr tags in root expr tag <expr type="binary"><expr><expr/></expr>
    public List<Expression> getExpressions() {
        return expressions;
    }

    @XmlElement(name = "expr")
    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }
    
    @Override
    public String toString(){
        String total = "" ;
        
        if(operator != null)
            total+= operator.getValue();
        
        if(atom != null)
            total+= atom.getValue();
        
        if(expressions != null)
            for(int i = 0; i < expressions.size(); i++)
                total+= expressions.get(i).toString();
        
        return total;
    }

}
// defining the operator tag
class Operator {
    private String value;

    public String getValue() {
        return value;
    }

    //there is an Atrribute called value in operator tag <operator value="*"></operator>
    @XmlAttribute
    public void setValue(String value) {
        this.value = value;
    }
}
// defining the atom tag
class Atom {
    private String value;

    public String getValue() {
        return value;
    }

    //there is an Atrribute called value in atom tag <atom value="3"></atom>
    @XmlAttribute
    public void setValue(String value) {
        this.value = value;
    }
}
