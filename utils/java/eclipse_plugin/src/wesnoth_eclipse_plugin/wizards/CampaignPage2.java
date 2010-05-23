/**
 * @author Timotei Dolean
 */
package wesnoth_eclipse_plugin.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import wesnoth_eclipse_plugin.utils.StringUtils;

public class CampaignPage2 extends WizardPage {
	private Text txtAbbrev_;
	private Text txtDefine_;
	private Text txtDifficulties_;
	private Text txtFirstScenario_;

	/**
	 * Create the wizard.
	 */
	public CampaignPage2() {
		super("wizardPage");
		setTitle("Campaign details");
		setDescription("Set the campaign details");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		ModifyListener modifyListener =  new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				updatePageIsComplete();
			}
		};

		SelectionListener selectionListener = new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				if (!(e.getSource() instanceof Button))
					return;
				String dif  = ((Button)e.getSource()).getText();

				if (!txtDifficulties_.getText().contains(dif))
				{
					txtDifficulties_.append(","+dif);
				}
				else
				{
					txtDifficulties_.setText(txtDifficulties_.getText().replace(dif, ""));
				}

				txtDifficulties_.setText(StringUtils.removeIncorrectCharacters(txtDifficulties_.getText(), ',',
						true,true));
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e)
			{
			}
		};

		container.setLayout(new GridLayout(5, false));

		Label lblAbbreviation = new Label(container, SWT.NONE);
		lblAbbreviation.setText("Abbreviation* :");

		txtAbbrev_ = new Text(container, SWT.BORDER);
		GridData gd_txtAbbrev_ = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txtAbbrev_.widthHint = 278;
		txtAbbrev_.setLayoutData(gd_txtAbbrev_);
		txtAbbrev_.addModifyListener(modifyListener);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		Label lblDefine = new Label(container, SWT.NONE);
		lblDefine.setText("Define* :");

		txtDefine_ = new Text(container, SWT.BORDER);
		GridData gd_txtDefine_ = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txtDefine_.widthHint = 207;
		txtDefine_.setLayoutData(gd_txtDefine_);
		txtDefine_.addModifyListener(modifyListener);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		Label lblDifficulties = new Label(container, SWT.NONE);
		lblDifficulties.setText("Difficulties:");

		txtDifficulties_ = new Text(container, SWT.BORDER);
		GridData gd_txtDifficulties_ = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txtDifficulties_.widthHint = 208;
		txtDifficulties_.setLayoutData(gd_txtDifficulties_);

				Button chkDiffEasy_ = new Button(container, SWT.CHECK);
				chkDiffEasy_.setText("EASY");
				chkDiffEasy_.addSelectionListener(selectionListener);

				Button chkDiffNormal_ = new Button(container, SWT.CHECK);
				chkDiffNormal_.setText("NORMAL");
				chkDiffNormal_.addSelectionListener(selectionListener);

				Button chkDiffHard_ = new Button(container, SWT.CHECK);
				chkDiffHard_.setText("HARD");
				chkDiffHard_.addSelectionListener(selectionListener);

		Label lblFirstScenario = new Label(container, SWT.NONE);
		lblFirstScenario.setText("First Scenario:");

		txtFirstScenario_ = new Text(container, SWT.BORDER);
		GridData gd_txtFirstScenario_ = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txtFirstScenario_.widthHint = 206;
		txtFirstScenario_.setLayoutData(gd_txtFirstScenario_);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		updatePageIsComplete();
	}

	/**
	 * Checks the mandatory fields and updates the isPageComplete status
	 */
	public void updatePageIsComplete()
	{
		setPageComplete(false);

		if (txtAbbrev_.getText().isEmpty())
		{
			setErrorMessage("Please specify an abbreviation.");
			return;
		}

		if (txtDefine_.getText().isEmpty())
		{
			setErrorMessage("Please specify a define.");
			return;
		}

		setErrorMessage(null);
		setPageComplete(true);
	}
	/**
	 * @return returns the abbreviation of the campaign
	 */
	public String getAbbrev() {
		return txtAbbrev_.getText();
	}
	/**
	 * @return returns the define of the campaign
	 */
	public String getDefine() {
		return txtDefine_.getText();
	}
	/**
	 * @return returns the difficulties of the campaign
	 */
	public String getDifficulties() {
		return txtDifficulties_.getText();
	}
	/**
	 * @return returns the first scenario of the campaign
	 */
	public String getFirstScenario() {
		return txtFirstScenario_.getText();
	}
}
