import { User } from "../user/user.types";
import { Comment } from "../comment/comment.types";

export interface Quizz
{
    id: string;
    name: string;
    description: string;
    dateDeCreation: string;
    createur: User;
    commentaires: Comment[];
    like: boolean;
    likeId: string;
    nbLike: number;
    realise: boolean;
    realiseId: string;
    nbRealise: number;
    cache: boolean;
}

