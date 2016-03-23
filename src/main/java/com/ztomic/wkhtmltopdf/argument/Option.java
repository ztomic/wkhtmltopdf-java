package com.ztomic.wkhtmltopdf.argument;

/**
 * Options generated for wkhtmltopdf 0.12.3 (with patched qt) from manpage
 */
public interface Option {

	String name();

	default String command() {
		if (name().length() == 1) {
			return "-" + name();
		}
		return "--" + name().replaceAll("(.)(\\p{Upper})", "$1-$2").toLowerCase();
	}

	default String print() {
		return getClass().getSimpleName() + "." + name() + "(" + command() + ")";
	}

	public enum GlobalOption implements Option {

		/**
		 * Collate when printing multiple copies (--collate)
		 */
		Collate("--collate"),
		/**
		 * Do not collate when printing multiple copies (--no-collate)
		 */
		NoCollate("--no-collate"),
		/**
		 * Read and write cookies from and to the supplied cookie jar file
		 * (--cookie-jar &lt;path&gt;)
		 */
		CookieJar("--cookie-jar"),
		/**
		 * Number of copies to print into the pdf file (--copies &lt;number&gt;)
		 */
		Copies("--copies"),
		/**
		 * Change the dpi explicitly (this has no effect on X11 based systems)
		 * (--dpi &lt;dpi&gt;)
		 */
		Dpi("--dpi"),
		/**
		 * Display more extensive help, detailing less common command switches
		 * (--extended-help)
		 */
		ExtendedHelp("--extended-help"),
		/**
		 * PDF will be generated in grayscale (--grayscale)
		 */
		Grayscale("--grayscale"),
		/**
		 * Display help (--help)
		 */
		Help("--help"),
		/**
		 * Output program html help (--htmldoc)
		 */
		Htmldoc("--htmldoc"),
		/**
		 * When embedding images scale them down to this dpi (--image-dpi
		 * &lt;integer&gt;)
		 */
		ImageDpi("--image-dpi"),
		/**
		 * When jpeg compressing images use this quality (--image-quality
		 * &lt;integer&gt;)
		 */
		ImageQuality("--image-quality"),
		/**
		 * Output license information and exit (--license)
		 */
		License("--license"),
		/**
		 * Generates lower quality pdf/ps. Useful to shrink the result document
		 * space (--lowquality)
		 */
		Lowquality("--lowquality"),
		/**
		 * Output program man page (--manpage)
		 */
		Manpage("--manpage"),
		/**
		 * Set the page bottom margin (--margin-bottom &lt;unitreal&gt;)
		 */
		MarginBottom("--margin-bottom"),
		/**
		 * Set the page left margin (--margin-left &lt;unitreal&gt;)
		 */
		MarginLeft("--margin-left"),
		/**
		 * Set the page right margin (--margin-right &lt;unitreal&gt;)
		 */
		MarginRight("--margin-right"),
		/**
		 * Set the page top margin (--margin-top &lt;unitreal&gt;)
		 */
		MarginTop("--margin-top"),
		/**
		 * Set orientation to Landscape or Portrait (--orientation
		 * &lt;orientation&gt;)
		 */
		Orientation("--orientation"),
		/**
		 * Page height (--page-height &lt;unitreal&gt;)
		 */
		PageHeight("--page-height"),
		/**
		 * Set paper size to: A4, Letter, etc. (--page-size &lt;Size&gt;)
		 */
		PageSize("--page-size"),
		/**
		 * Page width (--page-width &lt;unitreal&gt;)
		 */
		PageWidth("--page-width"),
		/**
		 * Do not use lossless compression on pdf objects (--no-pdf-compression)
		 */
		NoPdfCompression("--no-pdf-compression"),
		/**
		 * Be less verbose (--quiet)
		 */
		Quiet("--quiet"),
		/**
		 * Read command line arguments from stdin (--read-args-from-stdin)
		 */
		ReadArgsFromStdin("--read-args-from-stdin"),
		/**
		 * Output program readme (--readme)
		 */
		Readme("--readme"),
		/**
		 * The title of the generated pdf file (The title of the first document
		 * is used if not specified) (--title &lt;text&gt;)
		 */
		Title("--title"),
		/**
		 * Use the X server (some plugins and other stuff might not work without
		 * X11) (--use-xserver)
		 */
		UseXserver("--use-xserver"),
		/**
		 * Output version information and exit (--version)
		 */
		Version("--version");

		private final String command;

		GlobalOption(String command) {
			this.command = command;
		}

		public String command() {
			return command;
		}
	}

	public enum OutlineOption implements Option {

		/**
		 * Dump the default TOC xsl style sheet to stdout
		 * (--dump-default-toc-xsl)
		 */
		DumpDefaultTocXsl("--dump-default-toc-xsl"),
		/**
		 * Dump the outline to a file (--dump-outline &lt;file&gt;)
		 */
		DumpOutline("--dump-outline"),
		/**
		 * Put an outline into the pdf (--outline)
		 */
		Outline("--outline"),
		/**
		 * Do not put an outline into the pdf (--no-outline)
		 */
		NoOutline("--no-outline"),
		/**
		 * Set the depth of the outline (--outline-depth &lt;level&gt;)
		 */
		OutlineDepth("--outline-depth");

		private final String command;

		OutlineOption(String command) {
			this.command = command;
		}

		public String command() {
			return command;
		}
	}

	public enum PageOption implements Option {

		/**
		 * Allow the file or files from the specified folder to be loaded
		 * (repeatable) (--allow &lt;path&gt;)
		 */
		Allow("--allow"),
		/**
		 * Do print background (--background)
		 */
		Background("--background"),
		/**
		 * Do not print background (--no-background)
		 */
		NoBackground("--no-background"),
		/**
		 * Web cache directory (--cache-dir &lt;path&gt;)
		 */
		CacheDir("--cache-dir"),
		/**
		 * Use this SVG file when rendering checked checkboxes
		 * (--checkbox-checked-svg &lt;path&gt;)
		 */
		CheckboxCheckedSvg("--checkbox-checked-svg"),
		/**
		 * Use this SVG file when rendering unchecked checkboxes (--checkbox-svg
		 * &lt;path&gt;)
		 */
		CheckboxSvg("--checkbox-svg"),
		/**
		 * Set an additional cookie (repeatable), value should be url encoded.
		 * (--cookie &lt;name&gt; &lt;value&gt;)
		 */
		Cookie("--cookie"),
		/**
		 * Set an additional HTTP header (repeatable) (--custom-header
		 * &lt;name&gt; &lt;value&gt;)
		 */
		CustomHeader("--custom-header"),
		/**
		 * Add HTTP headers specified by --custom-header for each resource
		 * request. (--custom-header-propagation)
		 */
		CustomHeaderPropagation("--custom-header-propagation"),
		/**
		 * Do not add HTTP headers specified by --custom-header for each
		 * resource request. (--no-custom-header-propagation)
		 */
		NoCustomHeaderPropagation("--no-custom-header-propagation"),
		/**
		 * Show javascript debugging output (--debug-javascript)
		 */
		DebugJavascript("--debug-javascript"),
		/**
		 * Do not show javascript debugging output (--no-debug-javascript)
		 */
		NoDebugJavascript("--no-debug-javascript"),
		/**
		 * Add a default header, with the name of the page to the left, and the
		 * page number to the right, this is short for:
		 * --header-left='[webpage]' --header-right='[page]/[toPage]' --top 2cm
		 * --header-line (--default-header)
		 */
		DefaultHeader("--default-header"),
		/**
		 * Set the default text encoding, for input (--encoding
		 * &lt;encoding&gt;)
		 */
		Encoding("--encoding"),
		/**
		 * Do not make links to remote web pages (--disable-external-links)
		 */
		DisableExternalLinks("--disable-external-links"),
		/**
		 * Make links to remote web pages (--enable-external-links)
		 */
		EnableExternalLinks("--enable-external-links"),
		/**
		 * Do not turn HTML form fields into pdf form fields (--disable-forms)
		 */
		DisableForms("--disable-forms"),
		/**
		 * Turn HTML form fields into pdf form fields (--enable-forms)
		 */
		EnableForms("--enable-forms"),
		/**
		 * Do load or print images (--images)
		 */
		Images("--images"),
		/**
		 * Do not load or print images (--no-images)
		 */
		NoImages("--no-images"),
		/**
		 * Do not make local links (--disable-internal-links)
		 */
		DisableInternalLinks("--disable-internal-links"),
		/**
		 * Make local links (--enable-internal-links)
		 */
		EnableInternalLinks("--enable-internal-links"),
		/**
		 * Do not allow web pages to run javascript (--disable-javascript)
		 */
		DisableJavascript("--disable-javascript"),
		/**
		 * Do allow web pages to run javascript (--enable-javascript)
		 */
		EnableJavascript("--enable-javascript"),
		/**
		 * Wait some milliseconds for javascript finish (--javascript-delay
		 * &lt;msec&gt;)
		 */
		JavascriptDelay("--javascript-delay"),
		/**
		 * Specify how to handle pages that fail to load: abort, ignore or skip
		 * (--load-error-handling &lt;handler&gt;)
		 */
		LoadErrorHandling("--load-error-handling"),
		/**
		 * Specify how to handle media files that fail to load: abort, ignore or
		 * skip (--load-media-error-handling &lt;handler&gt;)
		 */
		LoadMediaErrorHandling("--load-media-error-handling"),
		/**
		 * Do not allowed conversion of a local file to read in other local
		 * files, unless explicitly allowed with --allow
		 * (--disable-local-file-access)
		 */
		DisableLocalFileAccess("--disable-local-file-access"),
		/**
		 * Allowed conversion of a local file to read in other local files.
		 * (--enable-local-file-access)
		 */
		EnableLocalFileAccess("--enable-local-file-access"),
		/**
		 * Minimum font size (--minimum-font-size &lt;int&gt;)
		 */
		MinimumFontSize("--minimum-font-size"),
		/**
		 * Do not include the page in the table of contents and outlines
		 * (--exclude-from-outline)
		 */
		ExcludeFromOutline("--exclude-from-outline"),
		/**
		 * Include the page in the table of contents and outlines
		 * (--include-in-outline)
		 */
		IncludeInOutline("--include-in-outline"),
		/**
		 * Set the starting page number (--page-offset &lt;offset&gt;)
		 */
		PageOffset("--page-offset"),
		/**
		 * HTTP Authentication password (--password &lt;password&gt;)
		 */
		Password("--password"),
		/**
		 * Disable installed plugins (--disable-plugins)
		 */
		DisablePlugins("--disable-plugins"),
		/**
		 * Enable installed plugins (plugins will likely not work)
		 * (--enable-plugins)
		 */
		EnablePlugins("--enable-plugins"),
		/**
		 * Add an additional post field (repeatable) (--post &lt;name&gt;
		 * &lt;value&gt;)
		 */
		Post("--post"),
		/**
		 * Post an additional file (repeatable) (--post-file &lt;name&gt;
		 * &lt;path&gt;)
		 */
		PostFile("--post-file"),
		/**
		 * Use print media-type instead of screen (--print-media-type)
		 */
		PrintMediaType("--print-media-type"),
		/**
		 * Do not use print media-type instead of screen (--no-print-media-type)
		 */
		NoPrintMediaType("--no-print-media-type"),
		/**
		 * Use a proxy (--proxy &lt;proxy&gt;)
		 */
		Proxy("--proxy"),
		/**
		 * Use this SVG file when rendering checked radiobuttons
		 * (--radiobutton-checked-svg &lt;path&gt;)
		 */
		RadiobuttonCheckedSvg("--radiobutton-checked-svg"),
		/**
		 * Use this SVG file when rendering unchecked radiobuttons
		 * (--radiobutton-svg &lt;path&gt;)
		 */
		RadiobuttonSvg("--radiobutton-svg"),
		/**
		 * Run this additional javascript after the page is done loading
		 * (repeatable) (--run-script &lt;js&gt;)
		 */
		RunScript("--run-script"),
		/**
		 * Disable the intelligent shrinking strategy used by WebKit that makes
		 * the pixel/dpi ratio none constant (--disable-smart-shrinking)
		 */
		DisableSmartShrinking("--disable-smart-shrinking"),
		/**
		 * Enable the intelligent shrinking strategy used by WebKit that makes
		 * the pixel/dpi ratio none constant (--enable-smart-shrinking)
		 */
		EnableSmartShrinking("--enable-smart-shrinking"),
		/**
		 * Stop slow running javascripts (--stop-slow-scripts)
		 */
		StopSlowScripts("--stop-slow-scripts"),
		/**
		 * Do not Stop slow running javascripts (--no-stop-slow-scripts)
		 */
		NoStopSlowScripts("--no-stop-slow-scripts"),
		/**
		 * Do not link from section header to toc (--disable-toc-back-links)
		 */
		DisableTocBackLinks("--disable-toc-back-links"),
		/**
		 * Link from section header to toc (--enable-toc-back-links)
		 */
		EnableTocBackLinks("--enable-toc-back-links"),
		/**
		 * Specify a user style sheet, to load with every page
		 * (--user-style-sheet &lt;url&gt;)
		 */
		UserStyleSheet("--user-style-sheet"),
		/**
		 * HTTP Authentication username (--username &lt;username&gt;)
		 */
		Username("--username"),
		/**
		 * Set viewport size if you have custom scrollbars or css attribute
		 * overflow to emulate window size (--viewport-size &lt;&gt;)
		 */
		ViewportSize("--viewport-size"),
		/**
		 * Wait until window.status is equal to this string before rendering
		 * page (--window-status &lt;windowStatus&gt;)
		 */
		WindowStatus("--window-status"),
		/**
		 * Use this zoom factor (--zoom &lt;float&gt;)
		 */
		Zoom("--zoom");

		private final String command;

		PageOption(String command) {
			this.command = command;
		}

		public String command() {
			return command;
		}
	}

	public enum HeaderAndFooterOption implements Option {

		/**
		 * Centered footer text (--footer-center &lt;text&gt;)
		 */
		FooterCenter("--footer-center"),
		/**
		 * Set footer font name (--footer-font-name &lt;name&gt;)
		 */
		FooterFontName("--footer-font-name"),
		/**
		 * Set footer font size (--footer-font-size &lt;size&gt;)
		 */
		FooterFontSize("--footer-font-size"),
		/**
		 * Adds a html footer (--footer-html &lt;url&gt;)
		 */
		FooterHtml("--footer-html"),
		/**
		 * Left aligned footer text (--footer-left &lt;text&gt;)
		 */
		FooterLeft("--footer-left"),
		/**
		 * Display line above the footer (--footer-line)
		 */
		FooterLine("--footer-line"),
		/**
		 * Do not display line above the footer (--no-footer-line)
		 */
		NoFooterLine("--no-footer-line"),
		/**
		 * Right aligned footer text (--footer-right &lt;text&gt;)
		 */
		FooterRight("--footer-right"),
		/**
		 * Spacing between footer and content in mm (--footer-spacing
		 * &lt;real&gt;)
		 */
		FooterSpacing("--footer-spacing"),
		/**
		 * Centered header text (--header-center &lt;text&gt;)
		 */
		HeaderCenter("--header-center"),
		/**
		 * Set header font name (--header-font-name &lt;name&gt;)
		 */
		HeaderFontName("--header-font-name"),
		/**
		 * Set header font size (--header-font-size &lt;size&gt;)
		 */
		HeaderFontSize("--header-font-size"),
		/**
		 * Adds a html header (--header-html &lt;url&gt;)
		 */
		HeaderHtml("--header-html"),
		/**
		 * Left aligned header text (--header-left &lt;text&gt;)
		 */
		HeaderLeft("--header-left"),
		/**
		 * Display line below the header (--header-line)
		 */
		HeaderLine("--header-line"),
		/**
		 * Do not display line below the header (--no-header-line)
		 */
		NoHeaderLine("--no-header-line"),
		/**
		 * Right aligned header text (--header-right &lt;text&gt;)
		 */
		HeaderRight("--header-right"),
		/**
		 * Spacing between header and content in mm (--header-spacing
		 * &lt;real&gt;)
		 */
		HeaderSpacing("--header-spacing"),
		/**
		 * Replace [name] with value in header and footer (repeatable)
		 * (--replace &lt;name&gt; &lt;value&gt;)
		 */
		Replace("--replace");

		private final String command;

		HeaderAndFooterOption(String command) {
			this.command = command;
		}

		public String command() {
			return command;
		}
	}

	public enum TOCOption implements Option {

		/**
		 * Do not use dotted lines in the toc (--disable-dotted-lines)
		 */
		DisableDottedLines("--disable-dotted-lines"),
		/**
		 * The header text of the toc (--toc-header-text &lt;text&gt;)
		 */
		TocHeaderText("--toc-header-text"),
		/**
		 * For each level of headings in the toc indent by this length
		 * (--toc-level-indentation &lt;width&gt;)
		 */
		TocLevelIndentation("--toc-level-indentation"),
		/**
		 * Do not link from toc to sections (--disable-toc-links)
		 */
		DisableTocLinks("--disable-toc-links"),
		/**
		 * For each level of headings in the toc the font is scaled by this
		 * factor (--toc-text-size-shrink &lt;real&gt;)
		 */
		TocTextSizeShrink("--toc-text-size-shrink"),
		/**
		 * Use the supplied xsl style sheet for printing the table of content
		 * (--xsl-style-sheet &lt;file&gt;)
		 */
		XslStyleSheet("--xsl-style-sheet");

		private final String command;

		TOCOption(String command) {
			this.command = command;
		}

		public String command() {
			return command;
		}
	}

}
