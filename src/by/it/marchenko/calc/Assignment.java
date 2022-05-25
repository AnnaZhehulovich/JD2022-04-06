package by.it.marchenko.calc;

import java.util.ArrayList;
import java.util.Objects;

import static by.it.marchenko.calc.MessageConst.*;

public class Assignment {
    private final Repository repository;

    public Assignment(Repository repository) {
        this.repository = repository;
    }

    public boolean isAssignmentAllowed(String expression, ArrayList<String> operands) throws CalcException {
        int firstAssignment = expression.indexOf(ASSIGN_OPERATOR);
        int secondAssignment = expression.indexOf(ASSIGN_OPERATOR, firstAssignment + 1);
        boolean assignmentEnabled = false;
        int varCounter = 0;
        if (secondAssignment > 0) {
            throw new CalcException(
                    ASSIGNMENT_EXCEPTION + ASSIGNMENT_OPERATOR_EXCEPTION + ASSIGNMENT_OPERATOR_COMMENT,
                    ASSIGN_OPERATOR, expression);
        } else if (firstAssignment > 0) {
            // A=2 1=2 A=B  || A=4 +B
            for (String operand : operands) {
                if (operand.matches(VARIABLE_PATTERN)) {
                    Var newVariable = repository.getVariable(operand);
                    varCounter++;
                    if (Objects.isNull(newVariable)) {
                        if (varCounter > 1) {
                            throw new CalcException(
                                    ASSIGNMENT_EXCEPTION + ASSIGNMENT_OPERAND_EXCEPTION + ASSIGNMENT_OPERAND_COMMENT,
                                    expression);
                        }
                        assignmentEnabled = true;
                        varCounter--;
                    }
                }
            }
            if (!assignmentEnabled && varCounter == 0) {
                throw new CalcException(
                        ASSIGNMENT_EXCEPTION + ASSIGNMENT_VARIABLE_EXCEPTION + ASSIGNMENT_VARIABLE_COMMENT,
                        expression);
            }
        }
        return (varCounter==1) || assignmentEnabled;
    }

    public Var performAssignment(ArrayList<String> operands, ArrayList<String> operators) {

        return null;
    }
}
