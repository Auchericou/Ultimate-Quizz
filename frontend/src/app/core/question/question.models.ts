import {Quizz} from "../quizz/quizz.types";

// -----------------------------------------------------------------------------------------------------
// @ Question
// -----------------------------------------------------------------------------------------------------
export class Question
{
    id: string;
    label: string;
    quizz: Quizz;
    answers: [];

    /**
     * Constructor
     */
    constructor(id, label, quizz, answers)
    {
        this.id = id || null;
        this.label = label || null;
        this.quizz = quizz || null;
        this.answers = answers || null;
    }
}
