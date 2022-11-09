export class User
{
    id: string;
    username: string;
    mail: string;
    firstname: string;
    lastname: string;
    historyQuizz: [];

    /**
     * Constructor
     */
    constructor(id, username, mail, firstname, lastname, historyQuizz)
    {
        this.id = id || null;
        this.username = username || null;
        this.mail = mail || null;
        this.firstname = firstname || null;
        this.lastname = lastname || null;
        this.historyQuizz = historyQuizz || null;
    }

}
