import {ChangeDetectionStrategy, ChangeDetectorRef, Component, ViewChild, ViewEncapsulation} from '@angular/core';
import {UserService} from "../../core/user/user.service";
import {Subject, takeUntil} from "rxjs";
import {User} from "../../core/user/user.model";
import {FormBuilder, FormGroup, NgForm, Validators} from "@angular/forms";
import {QuizzService} from "../../core/quizz/quizz.service";
import {Quizz} from "../../core/quizz/quizz.models";
import {FuseAlertType} from "../../../@fuse/components/alert";
import {FuseConfirmationService} from "../../../@fuse/services/confirmation";

@Component({
    selector       : 'quizz',
    templateUrl    : './quizz.component.html',
    encapsulation  : ViewEncapsulation.None,
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class QuizzComponent
{

    @ViewChild('challengeNgForm') challengeNgForm: NgForm;
    @ViewChild('commentNgForm') commentNgForm: NgForm;

    alert: { type: FuseAlertType; message: string } = {
        type   : 'success',
        message: ''
    };
    showAlert: boolean = false;

    alertComment: { type: FuseAlertType; message: string } = {
        type   : 'success',
        message: ''
    };
    showAlertComment: boolean = false;

    alertDeleteQuizz: { type: FuseAlertType; message: string } = {
        type   : 'success',
        message: ''
    };
    showAlertDeleteQuizz: boolean = false;

    private _unsubscribeAll: Subject<any> = new Subject<any>();
    user: User;
    quizzForm: FormGroup;
    quizzs: Quizz[];

    commentForm: FormGroup;
    selectedQuizz: Quizz;

    selectedQuizzMode = "all";
    createMode: boolean = false;

    /**
     * Constructor
     */
    constructor(private _changeDetectorRef: ChangeDetectorRef,
                private _userService: UserService,
                private _formBuilder: FormBuilder,
                private _quizzService: QuizzService,
                private _fuseConfirmationService: FuseConfirmationService
    )
    {
        // Subscribe to user changes
        this._userService.user$
            .pipe(takeUntil(this._unsubscribeAll))
            .subscribe((user: User) => {
                this.user = user;

                // Mark for check
                this._changeDetectorRef.markForCheck();
            });

        // Subscribe to quizzs changes
        this._quizzService.quizzs$
            .pipe(takeUntil(this._unsubscribeAll))
            .subscribe((quizzs) => {
                this.quizzs = quizzs;
                // Mark for check
                this._changeDetectorRef.markForCheck();
            });

    }

    // -----------------------------------------------------------------------------------------------------
    // @ Lifecycle hooks
    // -----------------------------------------------------------------------------------------------------

    /**
     * On init
     */
    ngOnInit(): void
    {
        // Create the challenge form
        this.quizzForm = this._formBuilder.group({
                name      : ['', [Validators.required, Validators.minLength(2), Validators.maxLength(63)]],
                description  : ['', [Validators.required, Validators.minLength(4), Validators.maxLength(139)]]
            }
        );

        // Create the comment form
        this.commentForm = this._formBuilder.group({
                texte  : ['', [Validators.required, Validators.minLength(4), Validators.maxLength(139)]]
            }
        );
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Create challenge
     */
    createQuizz(): void
    {
        // Return if the form is invalid
        if ( this.quizzForm.invalid )
        {
            return;
        }

        // Disable the form
        this.quizzForm.disable();

        // Hide the alert
        this.showAlert = false;

        // Add Quizz
        this._quizzService.createQuizz(this.quizzForm.controls['name'].value, this.quizzForm.controls['description'].value)
            .subscribe(
                () => {

                    this.alert = {
                        type   : 'success',
                        message: 'Défi crée avec succès.',

                    };

                    this.quizzForm.reset();
                },
                (error) => {

                    let errorMessage = error.error || error.errors.toString() || error.statusText || 'Une erreur est survenue. Veuillez re-essayer plus tard.';
                    this.alert = {
                        type   : 'error',
                        message: errorMessage
                    };

                },
                () => {
                    // Re-enable the form
                    this.quizzForm.enable();

                    // Show the alert
                    this.showAlert = true;

                    // Mark for check
                    this._changeDetectorRef.markForCheck();
                }

            );
    }

    /**
     * Switch challenge
     */
    switchSelectedQuizz(challenge){
        this.selectedQuizz = challenge;

        // Mark for check
        this._changeDetectorRef.markForCheck();
    }

    /**
     * Switch On create mode
     */
    switchOnCreateMode(){
        this.createMode = true;

        // Mark for check
        this._changeDetectorRef.markForCheck();
    }

    /**
     * Switch Off create mode
     */
    switchOffCreateMode(){
        this.createMode = false;

        // Mark for check
        this._changeDetectorRef.markForCheck();
    }

    /**
     * Get all challenge realised
     */
    getAll(){
        this.selectedQuizzMode = "all";

        this._quizzService.getAll()
            .subscribe(
                (quizzs) => {
                    this.quizzs = quizzs;
                    this._changeDetectorRef.markForCheck();
                }
            );

        // Mark for check
        this._changeDetectorRef.markForCheck();
    }

    /**
     * Get all challenge by creation date most old
     */
    getOldest(){
        this.selectedQuizzMode = "oldest";

        this._quizzService.getAll()
            .subscribe(
                (quizzs) => {
                    this.quizzs = quizzs;
                    this._changeDetectorRef.markForCheck();
                }
            );

        // Mark for check
        this._changeDetectorRef.markForCheck();
    }

    /**
     * Get all challenge realised
     */
    getAllRealised(){

        this.selectedQuizzMode = "realised";

        this._quizzService.getAllRealised()
            .subscribe(
                () => {
                    this._changeDetectorRef.markForCheck();
                }
            );

        // Mark for check
        this._changeDetectorRef.markForCheck();
    }

    /**
     * Get all challenge order by like DESC
     */
    getAllByLikeDesc(){

        this.selectedQuizzMode = "bestLike";

        this._quizzService.getAllByLike()
            .subscribe(
                () => {
                    this._changeDetectorRef.markForCheck();
                }
            );

        // Mark for check
        this._changeDetectorRef.markForCheck();
    }

    /**
     * Get all challenge order by like ASC
     */
    getAllByLikeAsc(){

        this.selectedQuizzMode = "minusLike";

        this._quizzService.getAllByLike('asc')
            .subscribe(
                () => {
                    this._changeDetectorRef.markForCheck();
                }
            );

        // Mark for check
        this._changeDetectorRef.markForCheck();
    }

    /**
     * Like challenge
     */
    like(challenge){

        this._quizzService.likeQuizz(challenge)
            .subscribe(
                () => {
                    challenge.like = true;
                },
                (error) => {
                    challenge.like = false;
                },()=>{

                    // Mark for check
                    this._changeDetectorRef.markForCheck();

                }
            );
    }

    /**
     * Unlike challenge
     */
    unlike(challenge){
        this._quizzService.unlikeQuizz(challenge)
            .subscribe(
                () => {
                    challenge.like = false;
                },
                (error) => {
                    challenge.like = true;
                },()=>{

                    // Mark for check
                    this._changeDetectorRef.markForCheck();

                }
            );
    }

    /**
     * Hide challenge
     */
    hideQuizz(challenge){

        this._quizzService.toggleHideQuizz(challenge, true)
            .subscribe(
                () => {
                    challenge.cache = true;
                },
                (error) => {
                    challenge.cache = false;
                },()=>{

                    // Mark for check
                    this._changeDetectorRef.markForCheck();

                }
            );
    }

    /**
     * UnHide challenge
     */
    unHideQuizz(challenge){
        this._quizzService.toggleHideQuizz(challenge, false)
            .subscribe(
                () => {
                    challenge.cache = false;
                },
                (error) => {
                    challenge.cache = true;
                },()=>{

                    // Mark for check
                    this._changeDetectorRef.markForCheck();

                }
            );
    }

    /**
     * Realised challenge
     */
    realisedQuizz(challenge){

        this._quizzService.realisedQuizz(challenge)
            .subscribe(
                () => {
                    challenge.realise = true;
                },
                (error) => {
                    challenge.realise = false;
                },()=>{

                    // Mark for check
                    this._changeDetectorRef.markForCheck();

                }
            );
    }

    /**
     * UnRealisedchallenge
     */
    unrealisedQuizz(challenge){
        this._quizzService.unrealisedQuizz(challenge)
            .subscribe(
                () => {
                    challenge.realise = false;
                },
                (error) => {
                    challenge.realise = true;
                },()=>{

                    // Mark for check
                    this._changeDetectorRef.markForCheck();

                }
            );
    }

    /**
     * Create comment
     */
    createComment(): void
    {
        // Return if the comment form is invalid
        if ( this.commentForm.invalid )
        {
            return;
        }

        // Disable the comment form
        this.commentForm.disable();

        // Hide the comment alert
        this.showAlertComment = false;

        // Add Comment
        this._quizzService.createComment(this.commentForm.controls['texte'].value, this.selectedQuizz.id)
            .subscribe(
                () => {
                    this.alertComment = {
                        type   : 'success',
                        message: 'Commentaire crée avec succès.',
                    };

                    this.commentForm.reset();
                },
                (error) => {

                    let errorMessage = error.error || error.errors.toString() || error.statusText || 'Une erreur est survenue. Veuillez re-essayer plus tard.';
                    this.alertComment = {
                        type   : 'error',
                        message: errorMessage
                    };

                },
                () => {
                    // Re-enable the comment form
                    this.commentForm.enable();

                    // Show the comment alert
                    this.showAlertComment = true;

                    // Mark for check
                    this._changeDetectorRef.markForCheck();
                }

            );
    }

    /**
     * Delete challenge
     */
    deleteQuizz(challenge): void
    {
        // Open the confirmation dialog
        const confirmation = this._fuseConfirmationService.open({
            title  : 'Suppression défi: "' + challenge.nom + '"',
            message: 'Etes-vous sûr de vouloir supprimer ce défi ?',
            actions: {
                confirm: {
                    label: 'Supprimer'
                }
            }
        });

        // Subscribe to the confirmation dialog closed action
        confirmation.afterClosed().subscribe((result) => {

            // If the confirm button pressed...
            if ( result === 'confirmed' )
            {

                // Delete the command on the server
                this._quizzService.deleteQuizz(challenge).subscribe(
                    () => {
                        this.alertDeleteQuizz = {
                            type   : 'success',
                            message: 'Défi supprimé.',
                        };
                    },
                    error => {
                        let errorMessage = error.error || error.errors.toString() || error.statusText || 'Une erreur est survenue. Veuillez re-essayer plus tard.';
                        this.alertDeleteQuizz = {
                            type   : 'error',
                            message: errorMessage
                        };

                    },
                    () => {
                        this.showAlertDeleteQuizz = true;

                        // Mark for check
                        this._changeDetectorRef.markForCheck();
                    }
                );
            }
        });
    }

}
